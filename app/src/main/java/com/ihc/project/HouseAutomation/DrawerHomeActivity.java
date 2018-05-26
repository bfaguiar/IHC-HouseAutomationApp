package com.ihc.project.HouseAutomation;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.HalfFloat;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;


public class DrawerHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentChangeListener, DataCommunicationHome {

    FrameLayout mainFrame;
    Toolbar toolbar;
    DrawerLayout drawer;
    ImageView logo;
    TextView name_drawer;
    TextView username_drawer;
    String getNameSTR;
    String getUserNameSTR;

    private HomeFragment homeFragment;
    private DevicesFragment devicesFragment;
    private DivisionsFragment divisionsFragment;
    private ArrayList<Device> devices = new ArrayList<Device>();

    private int IDButtonDevicesList;

    private static int PICK_IMAGE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_home);



        getWindow().setNavigationBarColor(android.support.v4.content.ContextCompat.getColor(this, R.color.colornavegationBarLogin));


        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        homeFragment = new HomeFragment();
        devicesFragment = new DevicesFragment();
        divisionsFragment = new DivisionsFragment();


        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mainFrame.setBackgroundResource(R.color.fragmentColor);
                        setFragment(homeFragment);
                        return true;
                    case R.id.navigation_divisions:
                        mainFrame.setBackgroundResource(R.color.fragmentColor);
                        setFragment(divisionsFragment);
                        return true;
                    case R.id.navigation_devices:
                        mainFrame.setBackgroundResource(R.color.colornavegationBarLogin);
                        setFragment(devicesFragment);
                        return true;
                }
                return false;
            }
        };


        // #################### NAVIGATION VIEW ##########################
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);

        // ################## TOOLBAR ######################

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        logo     = findViewById(R.id.logo_drawer);
        Glide.with(this).load(R.drawable.houseautomation_logo2).into(logo);

        // ###################### DRAWER ###########################

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headView = navigationView.getHeaderView(0);
        ImageView imageProfile=headView.findViewById(R.id.userimage_login);
        imageProfile.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent i = new Intent(
                                                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                                                startActivityForResult(i, PICK_IMAGE_REQUEST);
                                            }
                                        });


        Intent intent = getIntent();
        Bundle extrasBundle = intent.getExtras();
        boolean hasStr = false;
        if(!extrasBundle.isEmpty()) {
            hasStr = extrasBundle.containsKey("LoginData");
        }
        if(hasStr) {
            String[] str2 = extrasBundle.getStringArray("LoginData");

            name_drawer = headView.findViewById(R.id.name_login_drawer);
            username_drawer = headView.findViewById(R.id.username_login_drawer);
            name_drawer.setText(str2[1]);
            username_drawer.setText(str2[0]);
            Log.i("Tag"," OLA: " + str2[0] + str2[1]);
        }


    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

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
        getMenuInflater().inflate(R.menu.drawer_home, menu);
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

        if (id == R.id.usersettings) {

        } else if (id == R.id.logout) {
            startActivity(new Intent(this, MainActivity.class));
        }




        else if(id==R.id.AboutUSS) {


        }


         else if (id == R.id.help) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        ;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();

    }

    @Override
    public ArrayList<Device> getArrayListDevices() {
        return devices;
    }

    @Override
    public void setArrayListDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }

    @Override
    public int getIDButtonDevices() {
        return  IDButtonDevicesList;
    }

    @Override
    public void setIDButtonDevices(int IDButtonDevicesList) {
        this.IDButtonDevicesList = IDButtonDevicesList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                 Log.d("TAG", String.valueOf(bitmap));

                ImageView imageView = (ImageView) findViewById(R.id.userimage_login);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
