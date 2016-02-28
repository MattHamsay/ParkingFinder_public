package com.matthias.parkingfinder;

import android.graphics.Bitmap;

/**
 * Created by Matthias on 16-02-27.
 */
public class ParkingSpace
{
	// constant var for parking space parkingType
	enum ParkingType
	{ STREET_PARKING, PARKADE, SURFACE_LOT }

	// details of the parking space
	private Bitmap thumbnail;
	private String name;
	private Address address;
	private double price;
	private TimePeriod chargingTimePeriod;          // non-charging time = 24 hours - charging time - nonParkingTime
	private TimePeriod nonParkingTimePeriod;        // times when people are not allowed to park
	private ParkingType parkingType;
	private boolean hasCamera;
	private boolean hasAttendant;

	public ParkingSpace(Bitmap thumbnail, String name, Address address, double price,
	                    TimePeriod chargingTimePeriod, TimePeriod nonParkingTimePeriod,
	                    ParkingType parkingType, boolean hasCamera, boolean hasAttendant)
	{
		this.thumbnail = thumbnail;
		this.name = name;
		this.address = address;
		this.price = price;
		this.chargingTimePeriod = chargingTimePeriod;
		this.nonParkingTimePeriod = nonParkingTimePeriod;
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
