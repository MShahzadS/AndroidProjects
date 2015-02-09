package com.example.expensemanger;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	MyDataBase MyDb ;
	List<String>  myData ;
	List<String> months ;
    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DDBBBB"))) ;
        MyDb = new MyDataBase(getApplicationContext()) ;
        myData = new ArrayList<String>() ;
        months = new ArrayList<String>() ;
        
        ListView myList = (ListView) findViewById(R.id.yearList) ;
        try {
        MyDb.Open() ;
        months = MyDb.GetMnths() ;
        MyDb.close() ;
        
        
        ArrayAdapter<String> MyAdapt = new myAdapter(this,months);
		myList.setAdapter(MyAdapt) ;
		} catch(Exception e) {
			Log.e("Error",e.getMessage()) ;
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show() ;
		}
        myList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent("com.example.expensemanger.DayActivity") ;
				String mont[] = months.get(pos).split(" ") ;
				intent.putExtra("Month",mont[0] + "/" + mont[1]) ;
				
				startActivity(intent) ;
			}
		}) ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void Add_new(MenuItem m) {
    	Intent intent = new Intent("com.example.expensemanger.AddActivity") ;
    	startActivity(intent) ;
    }
}
