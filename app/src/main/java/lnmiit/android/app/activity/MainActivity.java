package lnmiit.android.app.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import lnmiit.android.app.R;
import lnmiit.android.app.fragment.AboutUsFragment;
import lnmiit.android.app.fragment.AcademicsFragment;
import lnmiit.android.app.fragment.AdministrationFragment;
import lnmiit.android.app.fragment.AdmissionsFragment;
import lnmiit.android.app.fragment.EmergencyFragment;
import lnmiit.android.app.fragment.FacultyFragment;
import lnmiit.android.app.fragment.GalleryFragment;
import lnmiit.android.app.fragment.HomeFragment;
import lnmiit.android.app.fragment.MapFragment;
import lnmiit.android.app.fragment.PlacementFragment;
import lnmiit.android.app.fragment.StudentFragment;

/* Created by Chanpreet
   on 11 August 2016
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ImageView imageView ;
    private CollapsingToolbarLayout collapsingToolbar;
    private RelativeLayout view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        view = (RelativeLayout)findViewById(R.id.view);
        collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        imageView = (ImageView)findViewById(R.id.backdrop);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = new HomeFragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout_container, fragment);
            fragmentTransaction.commit();
        }
    }

    public  TabLayout getTabLayout(){
        return  tabLayout;
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;

        int id = item.getItemId();
        if(id == R.id.home){
            view.setVisibility(View.VISIBLE);
            fragment = new HomeFragment();
            getSupportActionBar().setTitle("LNMIIT");
            tabLayout.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.lnmiit_main).into(imageView);
        } else if (id == R.id.academics) {
            view.setVisibility(View.VISIBLE);
            fragment = new AcademicsFragment();
            tabLayout.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle("Academics");
            Glide.with(this).load(R.drawable.academics).into(imageView);
        } else if (id == R.id.admission) {
            view.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.VISIBLE);
            fragment = new AdmissionsFragment();
            getSupportActionBar().setTitle("Admissions");
            Glide.with(this).load(R.drawable.admission).into(imageView);
        } else if (id == R.id.placement) {
            view.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.VISIBLE);
            fragment = new PlacementFragment();
            getSupportActionBar().setTitle("Placement");
            Glide.with(this).load(R.drawable.placement1).into(imageView);

        } else if (id == R.id.administration) {
            view.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.VISIBLE);
            fragment = new AdministrationFragment();
            getSupportActionBar().setTitle("Administration");
            Glide.with(this).load(R.drawable.admin).into(imageView);
        } else if (id == R.id.faculty) {
            view.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.VISIBLE);
            fragment = new FacultyFragment();
            getSupportActionBar().setTitle("Faculty");
            Glide.with(this).load(R.drawable.faculty).into(imageView);
        } else if (id == R.id.student) {
            view.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.VISIBLE);
            fragment = new StudentFragment();
            Glide.with(this).load(R.drawable.student).into(imageView);
            getSupportActionBar().setTitle("Student");
        } else if (id == R.id.emergency) {
            view.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.VISIBLE);
            fragment = new EmergencyFragment();
            getSupportActionBar().setTitle("Emergency");
            Glide.with(this).load(R.drawable.a14).into(imageView);
        } else if (id == R.id.map) {
            view.setVisibility(View.GONE);
            tabLayout.setVisibility(View.GONE);
            getSupportActionBar().setTitle("Map");
            fragment= new MapFragment();
        } else if (id == R.id.gallery) {
            view.setVisibility(View.GONE);
            tabLayout.setVisibility(View.GONE);
            fragment = new GalleryFragment();
            getSupportActionBar().setTitle("Gallery");
        } else if (id == R.id.about) {
            view.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.VISIBLE);
            fragment = new AboutUsFragment();
            getSupportActionBar().setTitle("About Us");
            Glide.with(this).load(R.drawable.aboutus).into(imageView);
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout_container, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}