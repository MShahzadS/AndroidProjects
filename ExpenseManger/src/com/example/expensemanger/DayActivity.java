package com.example.expensemanger;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DayActivity extends Activity {

	MyDataBase MyDb ;
	List<String> dates ;
	String month ;
	String[] temp ;
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_day);
		
		MyDb = new MyDataBase(this) ;
		dates = new ArrayList<String>() ;
		ListView myList = (ListView) findViewById(R.id.dayView) ;
		Intent intent = getIntent() ;
		month = intent.getStringExtra("Month") ;
		 getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DDBBBB"))) ;
		 temp = month.split("/") ;
				try {
	        MyDb.Open() ;
	        dates = MyDb.GetDates(month) ; 
	        MyDb.close() ;
	        
			//Toast.makeText(this,String.valueOf(myData.size()), Toast.LENGTH_SHORT).show() ;
			
			
			ArrayAdapter<String> MyAdapt = new myAdapter(DayActivity.this, dates);
			myList.setAdapter(MyAdapt) ;
			} catch (Exception e) {
				Log.e("Error",e.getMessage()) ;
				Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show() ;
			}
		myList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent("com.example.expensemanger.ViewActivity") ;
				intent.putExtra("Date",dates.get(pos) + "/" + month ) ;
				startActivity(intent) ;
			}
			
		}) ;
		
		myList.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				try {
				MyDb.Open() ;
				MyDb.deletebyDate(dates.get(arg2) + "/" + month) ;
				MyDb.close() ;
				Intent intent = new Intent("com.example.expensemanger.DayActivity") ;
				intent.putExtra("Month",month ) ;
				startActivity(intent) ;
				}catch (Exception e){ 
					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show() ;
				}
				return false;
			}
		}) ;

	}

	@SuppressLint("NewApi") @TargetApi(Build.VERSION_CODES.HONEYCOMB) @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.day, menu);
		ActionBar bar = getActionBar() ;
		bar.setTitle(month) ;
			
		
		
		return true;
	}

}
