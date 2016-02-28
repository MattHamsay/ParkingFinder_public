package com.matthias.parkingfinder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.*;

/**
 * Created by Matthias on 16-02-28.
 */
public class ParkingListActivity extends AppCompatActivity
{
	// Widgets on Layout
	private ListView            parkingListView;
	private TextView            textView_sortByCategory;
	private TextView            textView_sortOrder;

	// data for list
	private List<ParkingSpace>  parkingListData;
	private Address             currentUserLocation;

	// constant for bundle
	static final String         KEY_PARKING_LIST_DATA       = "PARKING_LIST_DATA";
	static final String         KEY_CURRENT_USER_LOCATION   = "CURR_USER_LOCATION";

	// constant format for textView.setText()
	private static final String FORMAT_SORT_CATEGORY        = "Arrange By %s ▾";
	private static final String FORMAT_SORT_ORDER           = "%s ▾";


	// temp var
	private final boolean       USING_STUB_DB = true;

	// set of constant Strings for dialog results
	private enum SortOption
	{
		STRING_DISTANCE("Distance"), STRING_COST("Cost"),
		STRING_ASCENDING("Ascending"), STRING_DESCENDING("Descending"),
		STRING_CANCEL("Cancel");

		private final String text;

		SortOption(final String text)
		{ this.text = text; }

		@Override
		public String toString()
		{ return text; }

		// for showSortCategoryOptionDialogBox().items
		private static final CharSequence[] getSortCategories()
		{
			int numItems = 3;

			CharSequence[] items = new CharSequence[numItems];
			items[0] = STRING_DISTANCE.toString();
			items[1] = STRING_COST.toString();
			items[2] = STRING_CANCEL.toString();

			return items;
		}

		// for showSortOrderOptionDialogBox().items
		private static final CharSequence[] getSortOrders()
		{
			int numItems = 3;

			CharSequence[] items = new CharSequence[numItems];
			items[0] = STRING_ASCENDING.toString();
			items[1] = STRING_DESCENDING.toString();
			items[2] = STRING_CANCEL.toString();

			return items;
		}
	}


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parking_list);

		// find buttons
		textView_sortByCategory = (TextView) findViewById(R.id.textView_ParkingList_arrangeBy);
		textView_sortOrder      = (TextView) findViewById(R.id.textView_ParkingList_sortOrder);

		// assign click listeners to buttons
		textView_sortByCategory.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				showSortCategoryOptionDialogBox();
			}
		});

		textView_sortOrder.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				showSortOrderOptionDialogBox();
			}
		});

		// STUB DB SPACE
		if (USING_STUB_DB)
		{
			parkingListData = getStubList();
			currentUserLocation = new Address("currentAdd", 12, "ABCDEF");
		}
		else
		{
			// fetch data from bundle
			parkingListData = (List<ParkingSpace>) savedInstanceState.get(KEY_PARKING_LIST_DATA);
			currentUserLocation = (Address) savedInstanceState.get(KEY_CURRENT_USER_LOCATION);
		}

		// initialise the l
		// ist
		parkingListView = (ListView) findViewById(R.id.listView_ParkingList_parkingList);
		parkingListView.setAdapter(new ParkingListAdapter(this, parkingListData, currentUserLocation));

		// sort with distance by default
		textView_sortByCategory.setText(String.format(FORMAT_SORT_CATEGORY, SortOption.STRING_DISTANCE.toString()));
		textView_sortOrder.setText(String.format(FORMAT_SORT_ORDER, SortOption.STRING_ASCENDING.toString()));
		sortList();
	}

	// ================================================================
	// Methods for Sort Option Dialog
	// ================================================================

	private void showSortCategoryOptionDialogBox()
	{
		// Distance from me == Distance from my destination, because myLocation == Destination
		final CharSequence[] items = SortOption.getSortCategories();

		AlertDialog.Builder builder = new AlertDialog.Builder(ParkingListActivity.this);
		builder.setTitle("Arrange By");
		builder.setItems(items, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int item)
			{
				if (!items[item].equals(SortOption.STRING_CANCEL.toString()))
				{
					// update the text of the arrange option button
					textView_sortByCategory.setText(String.format(FORMAT_SORT_CATEGORY, items[item]));
					sortList();
				}
			}
		});

		builder.show();
	}


	private void showSortOrderOptionDialogBox()
	{
		// Distance from me == Distance from my destination, because myLocation == Destination
		final CharSequence[] items = SortOption.getSortOrders();

		AlertDialog.Builder builder = new AlertDialog.Builder(ParkingListActivity.this);
		builder.setTitle("Arrange By");
		builder.setItems(items, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int item)
			{
				if (!items[item].equals(SortOption.STRING_CANCEL.toString()))
				{
					textView_sortOrder.setText(String.format(FORMAT_SORT_ORDER, items[item].toString()));
					sortList();
				}
			}
		});

		builder.show();
	}


	// ================================================================
	// Methods for Dialog Listeners and Sort Options
	// ================================================================

	private void sortList()
	{
		// do sort operation with specified order - this.parkingListData will be updated
		if (isSortByDistance())
			sortListByDistance();
		else if (isSortByCost())
			sortListByPrice();

		if (isDescending())
			Collections.reverse(parkingListData);       // ascending sort by default

		// prompt listview that item has changed
		parkingListView.setAdapter(new ParkingListAdapter(this, parkingListData, currentUserLocation));
		((ParkingListAdapter) parkingListView.getAdapter()).notifyDataSetChanged();
	}

	private void sortListByDistance()
	{
		List<ParkingSpace> sorted   = new ArrayList<>();
		List<ParkingSpace> unsorted = new ArrayList<>(parkingListData);

		List<Time> sortGuide = new ArrayList<>();

		for (ParkingSpace parkingSpace : unsorted)
			sortGuide.add(GoogleMapAPI.getDrivingTimeBetween(currentUserLocation, parkingSpace.getAddress()));

		Collections.sort(sortGuide, Time.Comparators.TOTAL_MINUTES);

		for (ParkingSpace parkingSpace : unsorted)
		{
			Time distanceInTime = GoogleMapAPI.getDrivingTimeBetween(currentUserLocation, parkingSpace.getAddress());

			boolean processing = true;
			for (int i = 0; i < sortGuide.size() && processing; i++)
			{
				if (distanceInTime.equals(sortGuide.get(i)))
				{
					processing = false;
					sortGuide.remove(i);                // this marker is consumed
//					unsorted.remove(parkingSpace);      // this item is processed
					sorted.add(parkingSpace);           // add removed item to sorted list
				}
			}
		}

		parkingListData = sorted;
	}

	private void sortListByPrice()
	{ Collections.sort(parkingListData, ParkingSpace.Comparators.PRICE); }

	boolean isSortByDistance()
	{ return textView_sortByCategory.getText().toString().contains(SortOption.STRING_DISTANCE.toString()); }

	boolean isSortByCost()
	{ return textView_sortByCategory.getText().toString().contains(SortOption.STRING_COST.toString()); }

	boolean isDescending()
	{ return textView_sortOrder.getText().toString().contains(SortOption.STRING_DESCENDING.toString()); }

	// ================================================================
	// Methods for stub DB
	// ================================================================

	ArrayList<ParkingSpace> getStubList()
	{
//		ParkingSpace A = null;
//		ParkingSpace B = null;
//		// ...
//
//		list.add(A);
//		list.add(B);
//		// ...

//		return list;

		Bitmap      thumbnail           = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_thumbnail_parkade_default);
		String      parkingName         = "My Fake Parking";
		String      streetName          = "Fake Street";
		int         streetNumber        = 1;
		String      zipCode             = "R3T 2N2";
		TimePeriod chargingTimePeriod = new TimePeriod(1, 3);
		TimePeriod nonParkingTimePeriod = new TimePeriod(2, 4);
		ParkingSpace.ParkingType type   = ParkingSpace.ParkingType.PARKADE;
		boolean     hasCamera           = true;
		boolean     hasAttendant        = true;

		ArrayList<ParkingSpace> list = new ArrayList<>();

		for (int i = 0; i < 15; i++)
		{
			Address address = new Address(streetName, streetNumber + i, zipCode);
			double  price   = 20.5 - i;

			ParkingSpace foo = new ParkingSpace(thumbnail, parkingName, address, price,
			                                    chargingTimePeriod, nonParkingTimePeriod,
			                                    type, hasCamera, hasAttendant);
			list.add(foo);
		}

		return list;
	}
}
