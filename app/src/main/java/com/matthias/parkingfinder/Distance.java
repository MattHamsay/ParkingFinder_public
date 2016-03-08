package com.matthias.parkingfinder;

import java.io.Serializable;

/**
 * Created by Matthias on 16-03-07.
 */
public class Distance implements Serializable
{
	private int kilometer;
	private int meter;
	private Time distanceInTime;    // driving time

	// for current user location
	public Distance()
	{
		this.kilometer = 0;
		this.meter = 0;
		this.distanceInTime = new Time(0, 0);
	};

	public Distance(double kilometer, Time distanceInTime)
	{
		this.kilometer = (int) kilometer;
		this.meter = (int) ((kilometer * 1000) % 1000);
		this.distanceInTime = distanceInTime;
	}

	public Distance(int kilometer, int meter, Time distanceInTime)
	{
		this.kilometer = kilometer;
		this.meter = meter;
		this.distanceInTime = distanceInTime;
	}

	public double getKilometer()
	{
		return kilometer + (meter / 1000.0);      // 1 + 400 = 1.4 km

	}

	public int getMeter()
	{
		return meter;
	}

	public Time getDistanceInTime()
	{
		return distanceInTime;
	}

	@Override
	public String toString()
	{
		if (kilometer > 0)
			return getKilometer() + "km";
		else
			return meter + "m";     // 400 m
	}
}
