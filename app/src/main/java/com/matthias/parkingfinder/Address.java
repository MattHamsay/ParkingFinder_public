package com.matthias.parkingfinder;

/**
 * Created by Matthias on 16-02-27.
 *
 * Example of Locations:
 * - Current Location of the user
 * - Destination (may not have both streetName / zipCode)
 * - Address of ParkingSpace
 *
 * Future Addition depending on Google Map API?
 * - Address of ... city, country? if required then such class members should be added to this class ...
 *
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
	double getDistanceInKmFrom(Address another)
	{
		return GoogleMapAPI.getDistanceInKmBetween(this, another);
	}

	String getDistanceInKmStringFrom(Address another)
	{ return String.format("%.1fkm", getDistanceInKmFrom(another)); }

	Time getDrivingTime(Address to)
	{ return GoogleMapAPI.getDrivingTimeBetween(this, to); }

}
