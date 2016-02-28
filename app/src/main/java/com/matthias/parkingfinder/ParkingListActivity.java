package com.matthias.parkingfinder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ParkingListActivity extends AppCompatActivity
{
	// Widgets on Layout
	private TextView textView_sortBy;

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

		// initialise the list with sort by distance
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
}
