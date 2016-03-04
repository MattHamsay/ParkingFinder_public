package com.matthias.parkingfinder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{
	private GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);

		// test ParkingListActivity
		startStubTestForParkingListActivity();
	}

	private void startStubTestForParkingListActivity()
	{
		// STUB DB SPACE
//		List<ParkingSpace> parkingSpaces = getStubList();
		boolean isStub = true;
		Address currentUserLocation = new Address("currentAdd", 12, "ABCDEF");
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
	public void onMapReady(GoogleMap googleMap)
	{
		Marker umLotUMarker;
		Marker umLotQMarker;
		mMap = googleMap;
		final LatLng U_OF_M = new LatLng(49.808, -97.137);

        //Move camera to testing location
        mMap.moveCamera(CameraUpdateFactory.newLatLng(U_OF_M));

		// University Of Manitoba U Lot
		LatLng umLotU = new LatLng(49.806, -97.141);
		umLotUMarker = mMap.addMarker(new MarkerOptions().position(umLotU).title("University Of Manitoba U Lot").snippet("Reserved 6am-4:30pm"));

        // University Of Manitoba Q Lot
		LatLng umLotQ = new LatLng(49.812, -97.139);
		umLotQMarker = mMap.addMarker(new MarkerOptions().position(umLotQ).title("University Of Manitoba Q Lot").snippet("Reserved 6am-4:30pm"));
	}
}
