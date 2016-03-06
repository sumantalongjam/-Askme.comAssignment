package com.sumanta.askme.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sumanta.askme.R;
import com.sumanta.askme.adapters.RecyclerAdapter;
import com.sumanta.askme.component.AskMeAsynTask;
import com.sumanta.askme.entities.DataEntity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ListView drawerListView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ProgressBar progressBar;
    private RecyclerAdapter adapter;
    private ArrayList<DataEntity> datatList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerListView = (ListView)findViewById(R.id.navigation_list);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter(MainActivity.this, datatList);
        recyclerView.setAdapter(adapter);
        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        populateData();
    }

    private void populateData() {
        AskMeAsynTask asynTask = new AskMeAsynTask(MainActivity.this, new AskMeAsynTask.TaskListener() {
            @Override
            public void onPreExecute() {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPostExecute(ArrayList<DataEntity> resultList) {
                progressBar.setVisibility(View.GONE);
                datatList.addAll(resultList);
                adapter.notifyDataSetChanged();

            }
        });
        asynTask.execute();
    }

    private void addDrawerItems() {
        String[] osArray = { "Home", "Login", "Advertise", "Review", "About Us", "Privacy Policy", "Terms Of Use", "Share"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        drawerListView.setAdapter(adapter);

        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Under Construction!", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search : {
                Toast.makeText(MainActivity.this, "Search!", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.action_card : {
                Toast.makeText(MainActivity.this, "Card!", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
