package com.matthias.parkingfinder;

/**
 * Created by Matthias on 16-02-27.
 *
 * Example of Locations:
 * - Current Location of the user
 * - Destination (may not have both streetName / zipCode)
 * - Location of ParkingSpace
 */
public class Location
{
	private String streetName;
	private String zipCode;

	public Location(String streetName, String zipCode)
	{
		this.streetName = streetName;
		this.zipCode = zipCode;             // R3T 2N2 => R3T2N2, not implemented as all are hard coded
	}

	/**
	 *
	 * @param another
	 * @return distance from another Location in terms of km. Maybe use
	 */
	double getDistanecFrom(Location another)
	{
		return -1;
	}
}
