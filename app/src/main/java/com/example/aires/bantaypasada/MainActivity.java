package com.example.aires.bantaypasada;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ValueFormatter {

    private static Intent intent;
    private DecimalFormat decimalFormat;

    //Pie Chart
    public MainActivity(){
        decimalFormat = new DecimalFormat("###,###,###");
    }
    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

        if(value < 10) return "";
        return decimalFormat.format(value) + "%";
    }
    //End Pie Chart

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.logo);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Button button1 = (Button) findViewById(R.id.ReportNew);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, reportNew.class);
                startActivity(intent);
//                Toast.makeText(MainActivity.this,"Report new",Toast.LENGTH_SHORT).show();
            }
        });

        Button button2 = (Button) findViewById(R.id.TrackLocation);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, myLocation.class);
                startActivity(intent);
            }
        });



        //Pie chart example
        ;
        PieChart pieChart = (PieChart) findViewById(R.id.chart);
        final ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(12, 0));
        entries.add(new Entry(4, 1));
        entries.add(new Entry(4, 2));
        pieChart.setUsePercentValues(true);


        PieDataSet dataset = new PieDataSet(entries, "");

        int cols[]={Color.parseColor("#a62224"),Color.parseColor("#FFF7C601"),Color.parseColor("#FFE75900")};

//        dataset.setColors(cols);

        // creating labels
        final ArrayList<String> labels = new ArrayList<String>();
        labels.add("Traffic Violation");
        labels.add("Driver's Attitude");
        labels.add("Others");


        PieData data = new PieData(labels, dataset); // initialize Piedata
        pieChart.setData(data); //set data into chart
        dataset.setSliceSpace(1);
        data.setValueTextSize(13);
        data.setValueTextColor(Color.WHITE);
        data.setValueFormatter(new MainActivity());

        dataset.setColors(cols); // set the color
        pieChart.animateY(1000);
        pieChart.setCenterText("Reports per day");
        pieChart.setCenterTextSize(14);
        pieChart.setDrawCenterText(true);
        pieChart.isDrawRoundedSlicesEnabled();
        pieChart.setDescription("");
        pieChart.setDrawSliceText(false);
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                Toast.makeText(MainActivity.this, labels.get(e.getXIndex()) + " = " + e.getVal() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        Legend legend = pieChart.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        legend.setTextSize(12);
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
        Intent intent = new Intent(MainActivity.this, Settings.class);

        if (id == R.id.about) {
            Toast.makeText(MainActivity.this, "About", Toast.LENGTH_LONG).show();
        } else if (id == R.id.privacy_policy) {
            Toast.makeText(MainActivity.this, "Privacy Policy", Toast.LENGTH_LONG).show();
        } else if (id == R.id.settings) {
            startActivity(intent);
        } else if (id == R.id.close) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
