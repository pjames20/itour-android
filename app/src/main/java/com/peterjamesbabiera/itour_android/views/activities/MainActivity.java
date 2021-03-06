package com.peterjamesbabiera.itour_android.views.activities;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.peterjamesbabiera.itour_android.R;
import com.peterjamesbabiera.itour_android.views.fragments.AboutFragment;
import com.peterjamesbabiera.itour_android.views.fragments.MapFragment;
import com.peterjamesbabiera.itour_android.views.fragments.contact.ContactFragment;
import com.peterjamesbabiera.itour_android.views.fragments.event.EventFragment;
import com.peterjamesbabiera.itour_android.views.fragments.site.SiteFragment;

/**
 * Created by peter on 2/9/18.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DialogInterface.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;

    private MapFragment mapFragment = new MapFragment();
    private EventFragment eventFragment = new EventFragment();
    private SiteFragment siteFragment = new SiteFragment();
    private ContactFragment contactFragment = new ContactFragment();
    private AboutFragment aboutFragment = new AboutFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.navigation);
        mNavigationView.setNavigationItemSelectedListener(this);

        setupDrawer();

        showFragment(mapFragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.action_menu_map:
                showFragment(mapFragment);
                getSupportActionBar().setTitle(getString(R.string.menu_map));
                return true;
            case R.id.action_menu_events:
                showFragment(eventFragment);
                getSupportActionBar().setTitle(getString(R.string.menu_events));
                return true;
            case R.id.action_menu_sites:
                showFragment(siteFragment);
                getSupportActionBar().setTitle(getString(R.string.menu_sites));
                return true;
            case R.id.action_menu_contacts:
                showFragment(contactFragment);
                getSupportActionBar().setTitle(getString(R.string.menu_contacts));
                return true;
            case R.id.action_menu_about:
                showFragment(aboutFragment);
                getSupportActionBar().setTitle(getString(R.string.menu_about));
                return true;
            default:
                return true;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.exit)
                .setPositiveButton(R.string.confirm, this)
                .setNegativeButton(R.string.cancel, this)
                .show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case DialogInterface.BUTTON_POSITIVE:
                finish();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                dialogInterface.cancel();
                break;
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void setupDrawer() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() == null) {
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.menu_map));
        }

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close);

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);

    }

    private void showFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }

}
