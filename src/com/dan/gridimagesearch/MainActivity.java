package com.dan.gridimagesearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements SearchFragment.Listener,SettingsFragment.Listener{
	private SearchFragment searchFragment;
	private ImageFragment imageFragment;
	private SettingsFragment settingsFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		searchFragment = new SearchFragment();
		searchFragment.setListener(this);
		imageFragment = new ImageFragment();
		settingsFragment = new SettingsFragment();
		settingsFragment.setListener(this);
		switchToFragment(searchFragment,false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	// Switch UI to the given fragment
    public void switchToFragment(Fragment newFrag,Boolean backStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(backStack){
        	ft.addToBackStack(null);
        }
        ft.replace(R.id.fragment_container, newFrag).commit();
    }

	@Override
	public void imageClicked(ImageResult image) {
		imageFragment.setCurrentImage(image);
		switchToFragment(imageFragment,true);
	}
	
	public void onClickSettings(MenuItem mi){
		switchToFragment(settingsFragment,true);
	}

	@Override
	public void settingsUpdated(Settings s) {
		searchFragment.settings = s;
		switchToFragment(searchFragment,true);
	}
	

}
