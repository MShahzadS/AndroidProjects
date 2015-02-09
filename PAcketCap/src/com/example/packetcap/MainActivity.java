package com.example.packetcap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.VpnService;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btn = (Button) findViewById(R.id.button1) ;
        try {
        ConnectivityManager cm =
                (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
         
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                              activeNetwork.isConnectedOrConnecting();
        if (isConnected)
        	btn.setEnabled(true) ;
        }catch(Exception e){
        	
        }
    }

    public void StartVPN(View v) {
    	
    	try {
    	Intent intent = VpnService.prepare(getApplicationContext()) ;
    	
    	if (intent != null){
    		startActivityForResult(intent, 0) ;
    	} else
    	onActivityResult(0, RESULT_OK, null);
    	} catch (Exception e){
    		Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show() ;
    	}
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  	  if (resultCode == RESULT_OK) {
  		Toast.makeText(getApplicationContext(), "Started activity", Toast.LENGTH_LONG).show() ;
  	  	Intent intent = new Intent(this, MyVpnService.class);
  	  	startService(intent);
  	  }
  	}   
}
