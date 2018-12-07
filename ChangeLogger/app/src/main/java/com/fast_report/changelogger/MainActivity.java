package com.fast_report.changelogger;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ChangesListFragment mChangesListFragment;
    ProductsFragment productsFragment;
    VersionsFragment versionsFragment;
    CRUDFragment crudFragment;
    AdminFragment adminFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        productsFragment = new ProductsFragment();
        versionsFragment = new VersionsFragment();
        crudFragment = new CRUDFragment();
        adminFragment = new AdminFragment();

        if (savedInstanceState == null){
            FragmentTransaction ftrans = getSupportFragmentManager().beginTransaction();
            ftrans.replace(R.id.fragment_container, mChangesListFragment);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction ftrans = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_changes) {
            ftrans.replace(R.id.fragment_container, mChangesListFragment);
        } else if (id == R.id.nav_products) {
            ftrans.replace(R.id.fragment_container, productsFragment);
        } else if (id == R.id.nav_versions) {
            ftrans.replace(R.id.fragment_container, versionsFragment);
        } else if (id == R.id.nav_crud) {
            ftrans.replace(R.id.fragment_container, crudFragment);
        } else if (id == R.id.nav_admin) {
            ftrans.replace(R.id.fragment_container, adminFragment);
        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_log_out) {

        }
        ftrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
