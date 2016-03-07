package com.matthias.parkingfinder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Matthias on 16-03-02.
 */
public class Database
{
	// ================================================================
	// Methods for stub DB
	// ================================================================

	public static ArrayList<ParkingSpace> getStubList(Context context, GoogleMap gMap)
	{
		Bitmap      thumbnail           = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_thumbnail_parkade_default);
		String      parkingName         = "My Fake Parking";
		String      streetName          = "Fake Street";
		int         streetNumber        = 1;
		String      zipCode             = "R3T 2N2";
		double      price               = 3.5;
		TimePeriod  chargingTime;
		TimePeriod  nonParkingTime;
		ParkingSpace.ParkingType type   = ParkingSpace.ParkingType.PARKADE;
		boolean     hasCamera           = true;
		boolean     hasAttendant        = true;
		ArrayList<ParkingSpace> list = new ArrayList<>();
		Address address;
		ParkingSpace foo;

		/*New Parking Addition Index 1*/
		LatLng latlng = new LatLng(49.806428, -97.141057);
		Marker marker = gMap.addMarker(new MarkerOptions().position(latlng).title("University Of Manitoba U Lot").snippet("Pay Parking 7:30-4:30pm"));

		chargingTime = new TimePeriod(0730,1630);
		nonParkingTime = new TimePeriod(0, 0);

		address = new Address("Chancellor Meatheson Rd", streetNumber , zipCode);
		foo = new ParkingSpace(thumbnail, "U Lot Parking", address, 1.5,
				chargingTime, nonParkingTime,
				ParkingSpace.ParkingType.SURFACE_LOT, hasCamera, hasAttendant, marker);
		foo.setMarkerFalse();
		list.add(foo);

		/*New Parking Addition Index 2*/
		latlng = new LatLng(49.810946, -97.138281);
		marker = gMap.addMarker(new MarkerOptions().position(latlng).title("University Of Manitoba Q Lot").snippet("Pay Parking 7:30-4:30pm"));

		chargingTime = new TimePeriod(0730,1630);
		nonParkingTime = new TimePeriod(0, 0);
		address = new Address("Dysart Rd", streetNumber , zipCode);
		foo = new ParkingSpace(thumbnail, "Q Lot Parking", address, 1.5,
				chargingTime, nonParkingTime,
				ParkingSpace.ParkingType.SURFACE_LOT, hasCamera, hasAttendant,marker);
		foo.setMarkerFalse();
		list.add(foo);

		/*New Parking Addition Index 3*/
		latlng = new LatLng(49.811246, -97.135759);
		marker = gMap.addMarker(new MarkerOptions().position(latlng).title("Science Parking Lot").snippet("Pay Parking 7:30-4:30pm"));

		chargingTime = new TimePeriod(0730,1630);
		nonParkingTime = new TimePeriod(0, 0);
		address = new Address("Sifton Rd", streetNumber , zipCode);
		foo = new ParkingSpace(thumbnail, "Science Building Parking", address, 1.5,
				chargingTime, nonParkingTime,
				ParkingSpace.ParkingType.SURFACE_LOT, hasCamera, hasAttendant, marker);
		foo.setMarkerFalse();
		list.add(foo);

		/*New Parking Addition Index 4*/
		latlng = new LatLng(49.811486, -97.128108);
		marker = gMap.addMarker(new MarkerOptions().position(latlng).title("University Of Manitoba B Lot").snippet("Pay Parking 7:30-4:30pm"));

		chargingTime = new TimePeriod(0730,1630);
		nonParkingTime = new TimePeriod(0, 0);
		address = new Address("Dysart Rd", streetNumber , zipCode);
		foo = new ParkingSpace(thumbnail, "B Lot Parking", address, 1.5,
				chargingTime, nonParkingTime,
				ParkingSpace.ParkingType.SURFACE_LOT, hasCamera, hasAttendant, marker);
		foo.setMarkerFalse();
		list.add(foo);

		return list;
	}

	ArrayList<ParkingSpace> getFilteredList(Context context, FilterOption filterOption)
	{
		return new ArrayList<>();
	}
}
