package com.example.expensemanger;

import android.R.integer;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class ViewActivity extends Activity {

	MyDataBase myDb ;
	MyData myData ;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		Intent intent =  getIntent() ;
		myDb  = new MyDataBase(this) ; 
		myData = new MyData() ;
		ActionBar  bar = getActionBar() ;
		String cdate = intent.getStringExtra("Date") ;
		bar.setTitle(cdate) ;
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DDBBBB"))) ;
		myDb.Open() ;
		myData = myDb.getData(cdate) ;
		myDb.close() ;
		
		TextView tvfoodv = (TextView) findViewById(R.id.tvfoodv) ;
		TextView tvbillv = (TextView) findViewById(R.id.tvBillv) ;
		TextView tvmediv = (TextView) findViewById(R.id.tvmediv) ;
		TextView tvtransv = (TextView) findViewById(R.id.tvtransv) ;
		TextView tvotherv = (TextView) findViewById(R.id.tvotherv) ;
		TextView tvtotalv = (TextView) findViewById(R.id.tvtotalv) ;
		
		
		tvfoodv.setText(String.valueOf(myData.getExpnses().getFood())) ;
		tvbillv.setText(String.valueOf(myData.getExpnses().getBill())) ;
		tvmediv.setText(String.valueOf(myData.getExpnses().getMedical())) ;
		tvtransv.setText(String.valueOf(myData.getExpnses().getTransport())) ;
		tvotherv.setText(String.valueOf(myData.getExpnses().getOthers())) ;
		
		int food = Integer.valueOf(myData.getExpnses().getFood()) ;
		int bill = Integer.valueOf(myData.getExpnses().getBill()) ;
		int trans = Integer.valueOf(myData.getExpnses().getTransport()) ;
		int med = Integer.valueOf(myData.getExpnses().getMedical()) ;
		int other = Integer.valueOf(myData.getExpnses().getOthers()) ;
		
		int Total = food + bill + trans + med + other ; 
		tvtotalv.setText(String.valueOf(Total)) ;
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view, menu);
		return true;
	}

}
