package com.matthias.parkingfinder;

import java.util.List;

/**
 * Created by Matthias on 16-02-27.
 */
public class Time
{
	// something like this
	private int from;
	private int to;

	public Time(int from, int to)
	{
		this.from = from;
		this.to = to;
	}

	/**
	 * 0000 - 2400 has overlap with 0100 - 0130.
	 * A.hasOverlapWith(B) is equivalent to B.hasOverlapWith(A).
	 *
	 * @param time
	 * @return
	 */
	boolean hasOverlapWith(Time time)
	{
		return false;
	}

	/**
	 * Example: Time (24 hours) - Time (0100 - 0130) = { {0000 - 0100}, {0130 - 2400} }
	 * Maybe useful to use with nonParkingTime.
	 * @param another
	 * @return A.exclude(B) == A - B
	 */
	List<Time> exclude(Time another)
	{
		return null;
	}

	// convert the distance into walking time
	static int getWalkingTime(double km)
	{
		return -1;
	}

	static int getDrivingTime(double km)
	{
		return -1;
	}

	/**
	 * Convert minutes to hour-minutes String representation
	 * @param minute
	 * @return XXh YYm if min > 60, 1h if min == 60, YYm if min < 60.
	 */
	static String convertMinToHour(int minute)
	{
		return null;
	}
}
