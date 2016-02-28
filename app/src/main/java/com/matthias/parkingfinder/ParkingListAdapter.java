package com.matthias.parkingfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Matthias on 16-02-28.
 */
public class ParkingListAdapter extends BaseAdapter
{
	Context context;
	ParkingSpace[] data;
	private static LayoutInflater inflater = null;

	public ParkingListAdapter(Context context, ParkingSpace[] data)
	{
		this.context = context;
		this.data = data;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount()
	{ return data.length; }

	@Override
	public Object getItem(int position)
	{ return data[position]; }

	@Override
	public long getItemId(int position)
	{ return position; }

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View listItemView = convertView;
		if (listItemView == null)
			listItemView = inflater.inflate(R.layout.listitem_parking_list, null);



		return null;
	}
}
