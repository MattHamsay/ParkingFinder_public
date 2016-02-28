package com.matthias.parkingfinder;

/**
 * Created by Matthias on 16-02-27.
 */
public class ParkingSpace
{
	private double cost;
	private Time chargingTime;          // non-charging time = 24 hours - charging time - nonParkingTime
	private Time nonParkingTime;        // times when people are not allowed to park
	private Location location;
	private boolean hasCamera;
	private boolean hasAttendant;

	public ParkingSpace(double cost, Time chargingTime, Time nonParkingTime, Location location, boolean hasCamera, boolean hasAttendant)
	{
		this.cost = cost;
		this.chargingTime = chargingTime;
		this.nonParkingTime = nonParkingTime;
		this.location = location;
		this.hasCamera = hasCamera;
		this.hasAttendant = hasAttendant;
	}


}
