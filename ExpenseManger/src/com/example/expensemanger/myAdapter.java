package com.example.expensemanger;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class myAdapter extends ArrayAdapter<String> {

	private Activity context ;
	List<String> myData ;
	String Display ;
	
	public myAdapter(Activity context,List<String> objects) {
		super(context,R.layout.adapter_view, objects);
		// TODO Auto-generated constructor stub
		myData =  new ArrayList<String>() ;
		this.context = context ;
		myData = objects ;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = context.getLayoutInflater() ;
		View RowView = inflater.inflate(R.layout.adapter_view, null,true) ;
		String temp  = "" ;
		TextView tv = (TextView) RowView.findViewById(R.id.txt_date) ;
		

		temp = myData.get(position) ;
		
		tv.setText(temp) ;
		return RowView;
		
		
	}
}
