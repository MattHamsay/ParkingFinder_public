package com.matthias.parkingfinder;

import java.util.ArrayList;

/**
 * Created by Matthias on 16-02-27.
 */
public class ParkingSpace
{
	// constant var for parking space parkingType
	enum ParkingType
	{
		STREET_PARKING, PARKADE, SURFACE_LOT;
	}
//	private final static int STREET_PARKING = 0;
//	private final static int PARKADE        = 1;
//	private final static int SURFACE_LOT    = 2;

	// details of the parking space
	private double cost;
	private Time chargingTime;          // non-charging time = 24 hours - charging time - nonParkingTime
	private Time nonParkingTime;        // times when people are not allowed to park
	private Address address;
	private boolean hasCamera;
	private boolean hasAttendant;
	private ParkingType parkingType;


	public ParkingSpace(double cost, Time chargingTime, Time nonParkingTime, Address address,
	                    boolean hasCamera, boolean hasAttendant, ParkingType parkingType)
	{
		this.cost = cost;
		this.chargingTime = chargingTime;
		this.nonParkingTime = nonParkingTime;
		this.address = address;
		this.hasCamera = hasCamera;
		this.hasAttendant = hasAttendant;
		this.parkingType = parkingType;
	}

	// ================================================================
	// Methods to check parking ParkingType
	// ================================================================

	boolean isStreetParking()
	{ return parkingType.equals(ParkingType.STREET_PARKING); }

	boolean isParkade()
	{ return parkingType.equals(ParkingType.PARKADE); }

	boolean isSurfaceLot()
	{ return parkingType.equals(ParkingType.SURFACE_LOT); }

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
