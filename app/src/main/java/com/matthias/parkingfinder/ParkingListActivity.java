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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthias on 16-02-28.
 */
public class ParkingListActivity extends AppCompatActivity
{
	// Widgets on Layout
	private TextView            textView_sortBy;

	// data for list
	private List<ParkingSpace>  parkingListData;
	private Address             currentUserLocation;

	// constant for bundle
	static final String         KEY_PARKING_LIST_DATA       = "PARKING_LIST_DATA";
	static final String         KEY_CURRENT_USER_LOCATION   = "CURR_USER_LOCATION";

	private final boolean       USING_STUB_DB = true;

	// set of constant Strings for dialog results
	private enum SortOption
	{
		STRING_DISTANCE("Distance"), STRING_COST("Cost"), STRING_CANCEL("Cancel");

		private final String text;

		SortOption(final String text)
		{ this.text = text; }

		@Override
		public String toString()
		{ return text; }

		// for showSortOptionDialogBox().items
		private static final CharSequence[] getSortOptions()
		{
			CharSequence[] items = new CharSequence[SortOption.values().length];
			for (int i = 0; i < SortOption.values().length; i++)
				items[i] = SortOption.values()[i].toString();
			return items;
		}
	}


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parking_list);

		// assign buttons
		textView_sortBy = (TextView) findViewById(R.id.textView_ParkingList_arrangeBy);
		textView_sortBy.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				showSortOptionDialogBox();
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

		// initialise the list
		ListView parkingListView = (ListView) findViewById(R.id.listView_ParkingList_parkingList);
		parkingListView.setAdapter(new ParkingListAdapter(this, parkingListData, currentUserLocation));

		// sort with distance by default
		sortListBy(SortOption.STRING_DISTANCE.toString());
	}

	// ================================================================
	// Methods for Sort Option Dialog
	// ================================================================

	private void showSortOptionDialogBox()
	{
		// Distance from me == Distance from my destination, because myLocation == Destination
		final CharSequence[] items = SortOption.getSortOptions();

		AlertDialog.Builder builder = new AlertDialog.Builder(ParkingListActivity.this);
		builder.setTitle("Arrange By");
		builder.setItems(items, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int item)
			{
				if (!items[item].equals(SortOption.STRING_CANCEL.toString()))
				{
					sortListBy(items[item].toString());
				}
			}
		});

		builder.show();
	}


	// ================================================================
	// Methods for Dialog Listeners
	// ================================================================

	private void sortListBy(String sortOption)
	{
		String format = "Arrange By %s ▾";
		textView_sortBy.setText(String.format(format, sortOption));
	}



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
		double      price               = 3.5;
		TimePeriod chargingTimePeriod = new TimePeriod(1, 3);
		TimePeriod nonParkingTimePeriod = new TimePeriod(2, 4);
		ParkingSpace.ParkingType type   = ParkingSpace.ParkingType.PARKADE;
		boolean     hasCamera           = true;
		boolean     hasAttendant        = true;

		ArrayList<ParkingSpace> list = new ArrayList<>();

		for (int i = 0; i < 15; i++)
		{
			Address address = new Address(streetName, streetNumber + i, zipCode);
			ParkingSpace foo = new ParkingSpace(thumbnail, parkingName, address, price,
			                                    chargingTimePeriod, nonParkingTimePeriod,
			                                    type, hasCamera, hasAttendant);
			list.add(foo);
		}

		return list;
	}
}
