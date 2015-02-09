package com.example.expensemanger;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends Activity {
	
	EditText etfood ;
	EditText etbill ;
	EditText etmed ;
	EditText ettrans ;
	EditText etother ;
	MyDataBase MyDb ;
	String Month[]= {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"} ;
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DDBBBB"))) ;
		MyDb = new MyDataBase(this) ;
		etfood = (EditText) findViewById(R.id.etFood) ;
		etbill = (EditText) findViewById(R.id.etBill) ;
		etmed = (EditText) findViewById(R.id.etMedi) ;
		ettrans = (EditText) findViewById(R.id.etTrans) ;
		etother = (EditText) findViewById(R.id.etOthers) ;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}
	public void AddNew(View v) {
		
		DatePicker Dp = (DatePicker) findViewById(R.id.datePicker1) ;
		String food = etfood.getText().toString() ;
		String bill = etbill.getText().toString() ;
		String medical = etmed.getText().toString() ;
		String trans = ettrans.getText().toString() ;
		String other = etother.getText().toString() ;
		String day = String.valueOf(Dp.getDayOfMonth()) ;
		String month = Month[Dp.getMonth()] ;
		String year = String.valueOf(Dp.getYear()) ;
		if (!(food.equals("") || bill.equals("") || medical.equals("") || trans.equals("") || other.equals("")))
		{
		MyDb.Open() ;
		MyDb.AddEntry(day,month,year,food, bill, medical, trans, other) ;
		MyDb.close() ;
		Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show() ;
		}
	}
	
}
