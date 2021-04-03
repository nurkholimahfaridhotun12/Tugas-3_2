package com.nur.shoppy;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.TextView;
import android.view.Menu;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
	private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String usersucceslogin = getIntent().getStringExtra("USERNAME");
		
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle (this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
		
		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
		View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.nav_header_textView);
		navUsername.setText(usersucceslogin);
		updateDisplay(0);
	}
	
	@Override
	public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
	public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
    @Override
	public boolean onOptionsItemSelected(MenuItem item){
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	public boolean onNavigationItemSelected(MenuItem item){
        switch (item.getItemId()) {
			case R.id.nav_home:
				updateDisplay(0);
				break;
            case R.id.nav_gallery:
				updateDisplay(1);
				break;
            case R.id.nav_slideshow:
				updateDisplay(2);
				break;
            case R.id.nav_developer:
				updateDisplay(3);
				break;
        }
		drawer.closeDrawer(GravityCompat.START);
        return true;
    }
	
	private void updateDisplay(int position) {
		Fragment fragment = null;
		switch (position) {
			case 0:
				fragment = new FragmentHome();
				break;
			case 1:
				fragment = new FragmentGallery();
				break;
			case 2:
				fragment = new FragmentSlideshow();
				break;
			case 3:
				fragment = new FragmentDeveloper();
				break;
			default:
				fragment = new FragmentHome();
				break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
			drawer.closeDrawers();
		}
	}
}
