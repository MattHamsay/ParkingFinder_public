package com.matthias.parkingfinder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

/**
 * Created by Matthias on 16-03-02.
 */
public class Database
{
	// ================================================================
	// Methods for stub DB
	// ================================================================

	ArrayList<ParkingSpace> getStubList(Context context)
	{
//		ParkingSpace A = null;
// 		ParkingSpace B = null;
//		// ...
//
//		list.add(A);
//		list.add(B);
//		// ...

//		return list;
		Bitmap      thumbnail           = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_thumbnail_parkade_default);
		String      parkingName         = "My Fake Parking";
		String      streetName          = "Fake Street";
		int         streetNumber        = 1;
		String      zipCode             = "R3T 2N2";
		double      price               = 3.5;
		TimePeriod  chargingTime        = new TimePeriod(0, 24);
		TimePeriod  nonParkingTime      = new TimePeriod(2, 4);
		ParkingSpace.ParkingType type   = ParkingSpace.ParkingType.PARKADE;
		boolean     hasCamera           = true;
		boolean     hasAttendant        = true;

		ArrayList<ParkingSpace> list = new ArrayList<>();

		for (int i = 0; i < 15; i++)
		{
			Address address = new Address(streetName, streetNumber + i, zipCode);
			ParkingSpace foo = new ParkingSpace(thumbnail, parkingName, address, price,
			                                    chargingTime, nonParkingTime,
			                                    type, hasCamera, hasAttendant);
			list.add(foo);
		}

		return list;
	}

	ArrayList<ParkingSpace> getFilteredList(Context context, FilterOption filterOption)
	{
		return new ArrayList<>();
	}
}
