package com.matthias.parkingfinder;

import java.util.ArrayList;

/**
 * Created by Matthias on 16-02-27.
 */
public class ParkingSpace
{
	// constant var for parking space type
	private final static int STREET_PARKING = 0;
	private final static int PARKADE        = 1;
	private final static int SURFACE_LOT    = 2;

	// details of the parking space
	private double cost;
	private Time chargingTime;          // non-charging time = 24 hours - charging time - nonParkingTime
	private Time nonParkingTime;        // times when people are not allowed to park
	private Location location;
	private boolean hasCamera;
	private boolean hasAttendant;
	private int type;


	public ParkingSpace(double cost, Time chargingTime, Time nonParkingTime, Location location,
	                    boolean hasCamera, boolean hasAttendant, int type)
	{
		this.cost = cost;
		this.chargingTime = chargingTime;
		this.nonParkingTime = nonParkingTime;
		this.location = location;
		this.hasCamera = hasCamera;
		this.hasAttendant = hasAttendant;
		this.type = type;
	}

	// ================================================================
	// Methods to check parking Type
	// ================================================================

	boolean isStreetParking()
	{ return type == STREET_PARKING; }

	boolean isSurfaceLot()
	{ return type == SURFACE_LOT; }

	boolean isParkade()
	{ return type == PARKADE; }


	// ================================================================
	// Methods for stub DB
	// ================================================================

	static ArrayList<ParkingSpace> getStubList()
	{
		ParkingSpace A = null;
		ParkingSpace B = null;
		// ...

		ArrayList<ParkingSpace> list = new ArrayList<>();
		list.add(A);
		list.add(B);

		return list;
	}
}
