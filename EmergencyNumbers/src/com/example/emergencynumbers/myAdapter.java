package com.example.emergencynumbers;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class myAdapter extends ArrayAdapter<CallNumbers> {

	private Activity context ;
	List<CallNumbers> myData ;
	List<String> Display ;
	int back ;
	
	public myAdapter(Activity context,List<CallNumbers> objects,int back) {
		super(context,R.layout.adapter_view, objects);
		// TODO Auto-generated constructor stub
		this.context = context ;
		myData = objects ;
		this.back = back ;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = context.getLayoutInflater() ;
		View RowView = inflater.inflate(R.layout.adapter_view, null,true) ;
		String temp  = "" ;
		TextView tv = (TextView) RowView.findViewById(R.id.txt_date) ;
		if (back == 1)
		temp = myData.get(position).getCategory() ;
		else
		temp = myData.get(position).getName() ;
		
		
		tv.setText(temp) ;
		return RowView;
		
		
	}
}
