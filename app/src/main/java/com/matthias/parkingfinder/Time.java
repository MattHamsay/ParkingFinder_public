package com.matthias.parkingfinder;

/**
 * Created by Matthias on 16-02-28.
 */
public class Time
{
	private int hour;
	private int min;

	public Time(int hour, int min)
	{
		this.hour = hour;
		this.min = min;
	}

	public int getHour()
	{ return hour; }

	public int getMin()
	{ return min; }

	// 23h 59m
	@Override
	public String toString()
	{ return String.format("%dh %dm", hour, min); }
}
