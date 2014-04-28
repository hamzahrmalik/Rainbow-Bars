package com.hamzah.rainbowbars;

import java.io.DataOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	//all the spinners
	Spinner statusbarspinner;
	Spinner navbarspinner;
	Spinner homebuttonspinner;
	Spinner backbuttonspinner;
	Spinner recentsbuttonspinner;
	//all the edittexts
	EditText statusbarcolour;
	EditText navbarcolour;
	EditText homebuttoncolour;
	EditText backbuttoncolour;
	EditText recentsbuttoncolour;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//init all the spinners and edittexts
		statusbarspinner = (Spinner) findViewById(R.id.statusbarspinner);
		navbarspinner = (Spinner) findViewById(R.id.navbarspinner);
		homebuttonspinner = (Spinner) findViewById(R.id.homebuttonspinner);
		backbuttonspinner = (Spinner) findViewById(R.id.backbuttonspinner);
		recentsbuttonspinner = (Spinner) findViewById(R.id.recentsbuttonspinner);
		
		statusbarcolour = (EditText) findViewById(R.id.statusbarcolour);
		navbarcolour = (EditText) findViewById(R.id.navbarcolour);
		homebuttoncolour = (EditText) findViewById(R.id.homebuttoncolour);
		backbuttoncolour = (EditText) findViewById(R.id.backbuttoncolour);
		recentsbuttoncolour = (EditText) findViewById(R.id.recentsbuttoncolour);
		//set listeners on all the spinners to enter the hex into the relevant edittext
		statusbarspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        statusbarcolour.setText(HexFromID(position));
		    }
		    
		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        
		    }

		});
		
		navbarspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        navbarcolour.setText(HexFromID(position));
		    }
		    
		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        
		    }

		});
		
		homebuttonspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        homebuttoncolour.setText(HexFromID(position));
		    }
		    
		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        
		    }

		});
		
		backbuttonspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        backbuttoncolour.setText(HexFromID(position));
		    }
		    
		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        
		    }

		});
		
		recentsbuttonspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        recentsbuttoncolour.setText(HexFromID(position));
		    }
		    
		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        
		    }

		});
	}
	
	public void apply(View v){
		//get pref
		@SuppressWarnings("deprecation")
		SharedPreferences pref = getSharedPreferences("pref", Context.MODE_WORLD_READABLE);
		Editor editor = pref.edit();
		//write prefs
		editor.putString(PrefKeys.STATUS_BAR_COLOUR, statusbarcolour.getText().toString());
		editor.putString(PrefKeys.NAVIGATION_BAR_COLOUR, navbarcolour.getText().toString());
		editor.putString(PrefKeys.HOME_BUTTON_COLOUR, homebuttoncolour.getText().toString());
		editor.putString(PrefKeys.BACK_BUTTON_COLOUR, backbuttoncolour.getText().toString());
		editor.putString(PrefKeys.RECENTS_BUTTON_COLOUR, recentsbuttoncolour.getText().toString());
		//apply
		editor.apply();
		//alert user
		Toast.makeText(this, "Changes applied. Restarting SystemUI...", Toast.LENGTH_LONG).show();
		//kill systemui
		killPackage("com.android.systemui");
	}
	
	//gets the hex code based on position in spinner
	public String HexFromID(int id){
		String s = null;
		switch(id){
		case 0:
			s="0";
			break;
		case 1:
			s="#FF0000";
			break;
		case 2:
			s="#04B404";
			break;
		case 3:
			s="#0000FF";
			break;
		case 4:
			s="#2ECCFA";
			break;
		case 5:
			s="#FF8000";
			break;
		case 6:
			s="#D7DF01";
			break;
		case 7:
			s="#9A2EFE";
			break;
		case 8:
			s="#FFFFFF";
			break;
		case 9:
			s="#000000";
			break;
		case 10:
			s="#6E6E6E";
			break;
			
		}
		return s;
	}
	
	public void openXDAThread(View v){
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://forum.xda-developers.com/xposed/modules/mod-rainbow-bars-multi-coloured-t2723879"));
		startActivity(browserIntent);
	}
	
	public static void killPackage(String packageToKill) { 
	        Process su = null; 
	        // get superuser 
	        try { 
	             
	            su = Runtime.getRuntime().exec("su"); 
	             
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } 
	        // kill given package 
	        if (su != null ){ 
	             
	            try { 
	                 
	                DataOutputStream os = new DataOutputStream(su.getOutputStream());  
	                os.writeBytes("pkill " + packageToKill + "\n"); 
	                os.flush(); 
	                os.writeBytes("exit\n"); 
	                os.flush(); 
	                su.waitFor(); 
	                 
	            } catch (IOException e) { 
	                 
	                e.printStackTrace(); 
	                 
	            } catch (InterruptedException e) { 
	                 
	                e.printStackTrace(); 
	                 
	            } 
	        } 
	    }  
	  

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
