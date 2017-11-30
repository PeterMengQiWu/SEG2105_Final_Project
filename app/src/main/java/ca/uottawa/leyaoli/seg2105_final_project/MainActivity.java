package ca.uottawa.leyaoli.seg2105_final_project;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Locale;




public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SectionPageAdapter a ;
    private ViewPager b;

    private FirebaseAuth mAuth;


    private static final String FILE_NAME = "file_lang"; // preference file name
    private static final String KEY_LANG = "key_lang"; // preference key

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> nameList;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);




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

        // =============== Data Base Test
        mAuth = FirebaseAuth.getInstance();
    }
    // Check
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null ){
            start();
        }
    }

    private void start() {
        Intent startPage = new Intent(MainActivity.this,StartPage.class);
        startActivity(startPage);
        finish();
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
        if (id == R.id.log_out) {
            FirebaseAuth.getInstance().signOut();
            start();
        }else if (id == R.id.lang_cn){
            Intent intent = new Intent(MainActivity.this,UserActivity.class);
            startActivity(intent);
        }

        return true;
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

    private void setupWithViewPager (ViewPager viewPager){

        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1(),"Shopping");
        adapter.addFragment(new Tab2(),"Tasks");
        adapter.addFragment(new Tab3(),"People");
        viewPager.setAdapter(adapter);
    }

    public void createNewTasks (View view){
        Intent newTasks = new Intent(MainActivity.this, AddChore.class);
        startActivityForResult(newTasks, 0);
    }

    public void  search (View view){
        listView = (ListView)findViewById(R.id.nameListView);
        nameList = new ArrayList<String>();
        taskList = new ArrayList<Task>();
        TasksDBHandler dbHandler = new TasksDBHandler(this);
        taskList = dbHandler.getTaskList();
        if (taskList != null) {
            for (int i = 0; i < taskList.size(); i++) {
                nameList.add(taskList.get(i).getName());
            }
        } else {
            Toast.makeText(MainActivity.this, "No Match Find", Toast.LENGTH_LONG).show();
        }
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, nameList);
        listView.setAdapter(arrayAdapter);
    }
}

