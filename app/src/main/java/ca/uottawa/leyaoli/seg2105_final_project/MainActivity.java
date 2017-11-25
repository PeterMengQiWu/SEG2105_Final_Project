package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SectionPageAdapter a ;
    private ViewPager b;
    private final  String[] userName = {"Dad","Mom","Son","Daughter","Baby"};
    private static ArrayList<String> list =new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i= 0 ; i< userName.length ; i++){
            list.add(userName[i]);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        a= new SectionPageAdapter(getSupportFragmentManager());
        b = (ViewPager) findViewById(R.id.container);
        setupWithViewPager(b);
        TabLayout tablayout = (TabLayout) findViewById(R.id.tablay);
        tablayout.setupWithViewPager(b);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.open_tasks) {
            // Handle the camera action
        } else if (id == R.id.nav_list) {

        } else if (id == R.id.nav_schedule) {

        } else if (id == R.id.task_backlog) {

        } else if (id == R.id.nav_people) {

        } else if (id == R.id.Cupboard_Fridge) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_maps) {
            Intent location = new Intent(MainActivity.this, LocationActivity.class);
            startActivity(location);
        } else if (id == R.id.nav_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void OnSetUserButton(View view) {
    //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), SwitchAccountActivity.class);
        startActivityForResult (intent,0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) return;
        //Getting the Avatar Image we show to our users
        ImageView avatarImage = (ImageView) findViewById(R.id.imageView);
        //Figuring out the correct image
        String drawableName = "ic_logo_00";
        switch (data.getIntExtra("imageID",R.id.imageView)) {
            case R.id.user0_img:
                drawableName = "ic_logo_01";
                break;
            case R.id.user1_img:
                drawableName = "ic_logo_02";
                break;
            case R.id.user2_img:
                drawableName = "ic_logo_03";
                break;
            case R.id.user3_img:
                drawableName = "ic_logo_04";
                break;
            case R.id.user4_img:
                drawableName = "ic_logo_05";
                break;
            case R.id.user5_img:
                drawableName = "ic_logo_00";
                break;
            case R.id.user6_img:
                drawableName = "ic_logo_00";
                break;
            case R.id.user7_img:
                drawableName = "ic_logo_00";
                break;
            case R.id.user8_img:
                drawableName = "ic_logo_00";
                break;
            default:
                drawableName = "ic_logo_00";
                break;
        }
        int resID = getResources().getIdentifier(drawableName, "drawable",
                getPackageName());
        avatarImage.setImageResource(resID);
    }

    private void setupWithViewPager (ViewPager viewPager){

        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1(),"tab1");
        adapter.addFragment(new Tab2(),"tab2");
        adapter.addFragment(new Tab3(),"tab3");
        viewPager.setAdapter(adapter);
    }

    public static ArrayList<String> getList() {
        return list;
    }
}
