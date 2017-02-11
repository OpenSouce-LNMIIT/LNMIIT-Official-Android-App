package lnmiit.android.app.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
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
import android.util.TypedValue;
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
import lnmiit.android.app.fragment.GalleryImageFragment;
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
    private AppBarLayout appBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appBar = (AppBarLayout)findViewById(R.id.appbar);
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
            fragment = new HomeFragment();
            lockAppBar(false,"Home");
            Glide.with(this).load(R.drawable.lnmiit_main).into(imageView);
        } else if (id == R.id.academics) {
            fragment = new AcademicsFragment();
            lockAppBar(false,"Academics");
            Glide.with(this).load(R.drawable.academics).into(imageView);
        } else if (id == R.id.admission) {

            fragment = new AdmissionsFragment();
            lockAppBar(false,"Admissions");
            Glide.with(this).load(R.drawable.admission).into(imageView);
        } else if (id == R.id.placement) {
            fragment = new PlacementFragment();
            lockAppBar(false,"Placements");
            Glide.with(this).load(R.drawable.placement1).into(imageView);

        } else if (id == R.id.administration) {
            fragment = new AdministrationFragment();
            lockAppBar(false,"Administartion");
            Glide.with(this).load(R.drawable.admin).into(imageView);
        } else if (id == R.id.faculty) {
            fragment = new FacultyFragment();
            lockAppBar(false,"Faculty");
            Glide.with(this).load(R.drawable.faculty).into(imageView);
        } else if (id == R.id.student) {
            fragment = new StudentFragment();
            lockAppBar(false,"Student");
            getSupportActionBar().setTitle("Student");
            Glide.with(this).load(R.drawable.student).into(imageView);
        } else if (id == R.id.emergency) {
            fragment = new EmergencyFragment();
            lockAppBar(false,"Emergency");
            Glide.with(this).load(R.drawable.a14).into(imageView);
        } else if (id == R.id.map) {
            fragment= new MapFragment();
            lockAppBar(true,"Reach Us");
        } else if (id == R.id.gallery) {
            lockAppBar(true,"Gallery");
            fragment = new GalleryImageFragment();
        } else if (id == R.id.about) {
            fragment = new AboutUsFragment();
            lockAppBar(false,"About us");
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

    public void lockAppBar(boolean locked,String title) {
        getSupportActionBar().setTitle(title);
        if(locked){
            tabLayout.setVisibility(View.GONE);
            appBar.setExpanded(false, true);
            int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)appBar.getLayoutParams();
            lp.height = px;
            appBar.setLayoutParams(lp);
        }else{
            tabLayout.setVisibility(View.VISIBLE);
            appBar.setExpanded(true, false);
            appBar.setActivated(true);
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) appBar.getLayoutParams();
            lp.height = (int) getResources().getDimension(R.dimen.app_bar_height);
        }
    }
}