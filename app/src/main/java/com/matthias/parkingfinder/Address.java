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
	private String streetName;
	private int streetNumber;       // may not have it
	private String zipCode;

	public Address(String streetName, int streetNumber, String zipCode)
	{
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.zipCode = zipCode;             // R3T 2N2 => R3T2N2, not implemented as all are hard coded
	}

	// some Address may not have street number
	boolean hasStreetNumber()
	{ return streetNumber > 0; }

	@Override
	public String toString()
	{
		if (hasStreetNumber())
			return streetNumber + " " + streetName + " " + zipCode;
		else
			return streetName + " " + zipCode;
	}

	// ================================================================
	// Methods for Comparing with Other Address
	// ================================================================

	/**
	 *
	 * @param another
	 * @return distance from another Address in terms of km. Maybe use
	 */
	double getDistanceFrom(Address another)
	{
		return -1;
	}

	String getDistanceStringFrom(Address another)
	{ return String.format("%.1fkm", getDistanceFrom(another)); }

	// convert the distance into walking time
	double getWalkingTime(double km)
	{
		return -1;
	}

	String getDrivingTimeString(Address to)
	{
		return "-23h -1m";
	}

	double getDrivingTime(Address to)
	{
		return -1;
	}

	double getDrivingTime(double km)
	{
		return -1;
	}

}
