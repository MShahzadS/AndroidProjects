package com.example.emergencynumbers;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	List<String> categories ;
	List<CallNumbers> myData ;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
        categories = new ArrayList<String>() ;
        myData = new ArrayList<CallNumbers>() ;
        String[] Data = getResources().getStringArray(R.array.MyData) ;
        
        for(int i=0;i<Data.length;i++){
        	String temp[] = Data[i].split(",") ;
        	if(!(categories.contains(temp[0]))) {
        		categories.add(temp[0]) ;
        		myData.add(new CallNumbers(temp[0],temp[1],temp[2])) ;
        	}
        }
        
        ListView catgryList =(ListView) findViewById(R.id.categorylist) ;
        ArrayAdapter<CallNumbers> adapt = new myAdapter(MainActivity.this, myData,1) ;
        catgryList.setAdapter(adapt) ;
        
        catgryList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("com.example.emergencynumbers.CallActivity") ;
				intent.putExtra("Category",myData.get(arg2).getCategory()) ;
				startActivity(intent) ;
				
			}
			
		}) ;
        }catch(Exception e) {
        	Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show() ;
        }
    }
    
}