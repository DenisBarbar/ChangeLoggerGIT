package com.fast_report.changelogger;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ChangesHeaderFragment mChangesHeaderFragment;
    ChangesListFragment mChangesListFragment;
    ProductsHeaderFragment mProductsHeaderFragment;
    ProductsListFragment mProductsListFragment;
    VersionsHeaderFragment mVersionsHeaderFragment;
    VersionsListFragment mVersionsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mChangesListFragment = new ChangesListFragment();
        mChangesHeaderFragment = new ChangesHeaderFragment();
        mProductsListFragment = new ProductsListFragment();
        mProductsHeaderFragment = new ProductsHeaderFragment();
        mVersionsListFragment = new VersionsListFragment();
        mVersionsHeaderFragment = new VersionsHeaderFragment();

        if (savedInstanceState == null){
            Log.d("MainActivity", "SaveInstance is null");
            FragmentTransaction ftrans = getSupportFragmentManager().beginTransaction();
            ftrans.replace(R.id.head_fragment, mChangesHeaderFragment);
            ftrans.replace(R.id.main_fragment, mChangesListFragment);
            ftrans.commit();
            navigationView.setCheckedItem(R.id.nav_changes);
        }

    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        FragmentTransaction ftrans = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_changes) {
            ftrans.replace(R.id.head_fragment, mChangesHeaderFragment);
            ftrans.replace(R.id.main_fragment, mChangesListFragment);
        } else if (id == R.id.nav_products) {
            ftrans.replace(R.id.head_fragment, mProductsHeaderFragment);
            ftrans.replace(R.id.main_fragment, mProductsListFragment);
        } else if (id == R.id.nav_versions) {
            ftrans.replace(R.id.head_fragment, mVersionsHeaderFragment);
            ftrans.replace(R.id.main_fragment, mVersionsListFragment);
        }
        ftrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("MainActivity", "onSaveInstanceState");
    }
}
