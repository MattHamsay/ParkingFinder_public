package com.matthias.parkingfinder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.format.*;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
	private static GoogleMap mMap;
	private  Database parkingspots = new Database();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);

		// test ParkingListActivity
		final Button button = (Button) findViewById(R.id.type);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onClickList(v);
			}
		});

	}

	private void startStubTestForParkingListActivity() {
		// STUB DB SPACE
//		List<ParkingSpace> parkingSpaces = getStubList();
		boolean isStub = true;
		Address currentUserLocation = new Address("currentAdd", 12, "ABCDEF", new Distance());
		FilterOption filterOption = new FilterOption(isStub, currentUserLocation);

		System.out.println("DEBUG: startStubTestForParkingListActivity() called:");
//		System.out.printf("DEBUG: size of list: %d, first parking name: %s\n", parkingSpaces.size(), parkingSpaces.get(0).getName());

		ParkingListActivity.startActivity(getApplicationContext(), filterOption);
	}


	/**
	 * Manipulates the map once available.
	 * This callback is triggered when the map is ready to be used.
	 * This is where we can add markers or lines, add listeners or move the camera. In this case,
	 * we just add a marker near Sydney, Australia.
	 * If Google Play services is not installed on the device, the user will be prompted to install
	 * it inside the SupportMapFragment. This method will only be triggered once the user has
	 * installed Google Play services and returned to the app.
	 */
	@Override
	public void onMapReady(GoogleMap googleMap) {
		Marker umLotUMarker;
		Marker umLotQMarker;
		mMap = googleMap;
		final LatLng U_OF_M = new LatLng(49.808, -97.137);
		final ArrayList<ParkingSpace> parkingSpaces =  parkingspots.getStubList(getApplicationContext(), mMap);
		//Move camera to testing location
		mMap.moveCamera(CameraUpdateFactory.newLatLng(U_OF_M));

		final Button priceButton = (Button) findViewById(R.id.price);
		priceButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onClickPrice(v, parkingSpaces);
			}
		});

		/**
		 * CHECKBOX FILTERS ARE WORKING HERE IS THE CODE PARKCADE
		 */
		CheckBox parkcadeBox = (CheckBox) findViewById(R.id.checkbox_parkade);
		parkcadeBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onCheckboxClicked(v, parkingSpaces);
			}
		});
		/**
		 * CHECKBOX FILTERS ARE WORKING HERE IS THE CODE LOT PARKING
		 */
		CheckBox streetBox = (CheckBox) findViewById(R.id.checkbox_lot);
		streetBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onCheckboxClicked(v, parkingSpaces);
			}
		});
		/**
		 * CHECKBOX FILTERS ARE WORKING HERE IS THE CODE STREET PARKING
		 */
		CheckBox lotBox = (CheckBox) findViewById(R.id.checkbox_street);
		lotBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onCheckboxClicked(v, parkingSpaces);
			}
		});



	}

	public static GoogleMap getMap()
	{
		return mMap;
	}

	/**
	 CHECK BOX FILTER CODE
	 */
	public void onCheckboxClicked(View view, ArrayList<ParkingSpace> list) {
		// Is the view now checked?
		boolean checked = ((CheckBox) view).isChecked();

		// Check which checkbox was clicked
		switch (view.getId()) {
			case R.id.checkbox_lot:
				if (checked) {
					for(int i = 0; i <list.size(); i++ ){
						if(!(list.get(i).isSurfaceLot())) {
							list.get(i).setMarkerFalse();
						}
					}
				} else {
					for(int i = 0; i <list.size(); i++ ) {
						if(!(list.get(i).isSurfaceLot())) {
							list.get(i).setMarkerTrue();
						}
					}
				}
				break;
			case R.id.checkbox_street:
				if (checked) {
					for(int i = 0; i <list.size(); i++ ){
						if(!(list.get(i).isStreetParking())) {
							list.get(i).setMarkerFalse();
						}
					}
				} else {
					for(int i = 0; i <list.size(); i++ ) {
						if(!(list.get(i).isStreetParking())) {
							list.get(i).setMarkerTrue();
						}
					}
				}
				break;
			case R.id.checkbox_parkade:
				if (checked) {
					for(int i = 0; i <list.size(); i++ ){
						if(!(list.get(i).isParkade())) {
							list.get(i).setMarkerFalse();
						}
					}
				} else {
					for(int i = 0; i <list.size(); i++ ) {
						if(!(list.get(i).isParkade())) {
							list.get(i).setMarkerTrue();
						}
					}
				}
				break;
			// TODO: Add in code to send to filters
		}
	}

	public void onClickPrice (View view, ArrayList<ParkingSpace> list){
		EditText priceHrText = (EditText)findViewById(R.id.pricehr);
		EditText priceFlatText = (EditText)findViewById(R.id.flatrate);
		String valueHr = priceHrText.getText().toString();
		String valueFlat = priceFlatText.getText().toString();
		double priceHr = 0;
		double priceFlat = 0;

		try {
			priceHr = Double.parseDouble(valueHr);
			priceFlat = Double.parseDouble(valueFlat);
		}
		catch(Exception e) {
			Log.e("logtag", "Exception: " + e.toString());
		}

		if(priceHr != 0)
		{
			for(int i = 0; i < list.size(); i++)
			{
				if(list.get(i).getPrice() > priceHr)
				{
					list.get(i).setMarkerFalse();
				}
			}
		}
		System.out.println("PRICE: " + priceHr);
	};

	public void onClickList(View view)
	{
		startStubTestForParkingListActivity();
	}
}//close map activity