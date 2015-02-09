package com.example.emergencynumbers;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CallActivity extends Activity {

	String[] data ;
    List<String> indx ;
	List<CallNumbers> myNumbers;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call);
		
		try {
		myNumbers = new ArrayList<CallNumbers>() ;
		data =  getResources().getStringArray(R.array.MyData) ;
		
		
		indx = new ArrayList<String>() ;
		
		for (int i=0;i<data.length;i++)
		{
			String temp[] = data[i].split(",") ;
			if (temp[0].equals(getIntent().getStringExtra("Category").toString()))
			myNumbers.add(new CallNumbers(temp[0], temp[1], temp[2])) ;
		}
		
        ListView NameList =(ListView) findViewById(R.id.NameList) ;
        ArrayAdapter<CallNumbers> adapt = new myAdapter(CallActivity.this, myNumbers,0) ;
        NameList.setAdapter(adapt) ;
        NameList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(Intent.ACTION_CALL) ;
				String nmb = "tel:" + myNumbers.get(arg2).getNumber() ;
				intent.setData(Uri.parse(nmb));
				startActivity(intent) ;
				
			}
		}) ;
		} catch(Exception e) {
			Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show() ;
		}
	
	}


}
