package com.matthias.parkingfinder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

/**
 * Created by Matthias on 16-02-27.
 */
public class ParkingSpace
{
	// constant var for parking space parkingType
	enum ParkingType
	{ STREET_PARKING, PARKADE, SURFACE_LOT }
//	private final static int STREET_PARKING = 0;
//	private final static int PARKADE        = 1;
//	private final static int SURFACE_LOT    = 2;

	// details of the parking space
	private Bitmap thumbnail;
	private String name;
	private Address address;
	private double price;
	private Time chargingTime;          // non-charging time = 24 hours - charging time - nonParkingTime
	private Time nonParkingTime;        // times when people are not allowed to park
	private ParkingType parkingType;
	private boolean hasCamera;
	private boolean hasAttendant;

	public ParkingSpace(Bitmap thumbnail, String name, Address address, double price,
	                    Time chargingTime, Time nonParkingTime,
	                    ParkingType parkingType, boolean hasCamera, boolean hasAttendant)
	{
		this.thumbnail = thumbnail;
		this.name = name;
		this.address = address;
		this.price = price;
		this.chargingTime = chargingTime;
		this.nonParkingTime = nonParkingTime;
		this.parkingType = parkingType;
		this.hasCamera = hasCamera;
		this.hasAttendant = hasAttendant;
	}

	// ================================================================
	// Getter Methods
	// ================================================================

	Bitmap getThumbnail()
	{ return thumbnail; }
	String getName()
	{ return name; }

	Address getAddress()
	{ return address; }

	double getPrice()
	{ return price; }

	String getPriceString()
	{ return String.format("$%.1f / hr", getPrice()); }


	// ================================================================
	// Methods to check parking ParkingType
	// ================================================================

	boolean isStreetParking()
	{ return parkingType.equals(ParkingType.STREET_PARKING); }

	boolean isParkade()
	{ return parkingType.equals(ParkingType.PARKADE); }

	boolean isSurfaceLot()
	{ return parkingType.equals(ParkingType.SURFACE_LOT); }
}
