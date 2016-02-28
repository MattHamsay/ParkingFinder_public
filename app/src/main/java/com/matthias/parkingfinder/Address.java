package com.matthias.parkingfinder;

/**
 * Created by Matthias on 16-02-27.
 *
 * Example of Locations:
 * - Current Location of the user
 * - Destination (may not have both streetName / zipCode)
 * - Address of ParkingSpace
 */
public class Address
{
	private int streetNumber;       // may not have it
	private String streetName;
	private String zipCode;

	public Address(String streetName, String zipCode)
	{
		this.streetName = streetName;
		this.zipCode = zipCode;             // R3T 2N2 => R3T2N2, not implemented as all are hard coded
	}

	// some Address may not have street number
	boolean hasStreetNumber()
	{ return streetNumber > 0; }

	/**
	 *
	 * @param another
	 * @return distance from another Address in terms of km. Maybe use
	 */
	double getDistanecFrom(Address another)
	{
		return -1;
	}
}
