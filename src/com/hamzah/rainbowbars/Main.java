package com.hamzah.rainbowbars; 

import android.graphics.Color;
import android.view.View;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;
 
public class Main implements IXposedHookInitPackageResources{

	@Override
	public void handleInitPackageResources(InitPackageResourcesParam resparam)
			throws Throwable {
		if(!resparam.packageName.equals("com.android.systemui"))
			return;
		
		XSharedPreferences pref = new XSharedPreferences(Main.class.getPackage().getName(), "pref");
		final String statusbarcolour = pref.getString(PrefKeys.STATUS_BAR_COLOUR, "0");
		final String navbarcolour = pref.getString(PrefKeys.NAVIGATION_BAR_COLOUR, "0");
		final String homebuttoncolour = pref.getString(PrefKeys.HOME_BUTTON_COLOUR, "0");
		final String backbuttoncolour = pref.getString(PrefKeys.BACK_BUTTON_COLOUR, "0");
		final String recentsbuttoncolour = pref.getString(PrefKeys.RECENTS_BUTTON_COLOUR, "0");
		
		resparam.res.hookLayout("com.android.systemui", "layout", "status_bar", new XC_LayoutInflated() {
			@Override
			public void handleLayoutInflated(LayoutInflatedParam liparam)
					throws Throwable {
				View statusbar = (View) liparam.view.findViewById(
	                    liparam.res.getIdentifier("icons", "id", "com.android.systemui"));
				if(!statusbarcolour.equals("0"))
				statusbar.setBackgroundColor(Color.parseColor(statusbarcolour));
			}
		});
		
		resparam.res.hookLayout("com.android.systemui", "layout", "navigation_bar", new XC_LayoutInflated() {
	        @Override
	        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
	        	View navbar = (View) liparam.view.findViewById(
	                    liparam.res.getIdentifier("nav_buttons", "id", "com.android.systemui"));
	        	
	            View homeButton = (View) liparam.view.findViewById(
	                    liparam.res.getIdentifier("home", "id", "com.android.systemui"));
	            
	            View backButton = (View) liparam.view.findViewById(
	                    liparam.res.getIdentifier("back", "id", "com.android.systemui"));
	            
	            View recentsButton = (View) liparam.view.findViewById(
	                    liparam.res.getIdentifier("recent_apps", "id", "com.android.systemui"));
	            //zero is default so dont do anything
	            if(!navbarcolour.equals("0"))
	            navbar.setBackgroundColor(Color.parseColor(navbarcolour));
	            if(!homebuttoncolour.equals("0"))
	            homeButton.setBackgroundColor(Color.parseColor(homebuttoncolour));
	            if(!backbuttoncolour.equals("0"))
	            backButton.setBackgroundColor(Color.parseColor(backbuttoncolour));
	            if(!recentsbuttoncolour.equals("0"))
	            recentsButton.setBackgroundColor(Color.parseColor(recentsbuttoncolour));
	        }
		});
		
		
		}
 
}