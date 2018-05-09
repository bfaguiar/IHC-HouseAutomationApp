package com.example.bruno.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.content.Intent;
import android.view.*;


 public class MainActivity extends AppCompatActivity
 {

    private Button bt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = (Button) findViewById(R.id.button_login);

        // set navitagtion bar color.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
            getWindow().setNavigationBarColor(android.support.v4.content.ContextCompat.getColor(this, R.color.colornavegationBarLogin));



        bt1.setOnClickListener( new Button.OnClickListener() {
           @Override
            public void onClick(View view) {

                launchActivity();
            }
        });


    }

    private void launchActivity() {

        startActivity(new Intent(this, HomeActivity.class));

    }
        /*
         *
        RelativeLayout layout1 = new RelativeLayout(this);
        layout1.setBackgroundColor(Color.DKGRAY);

        Button bt = new Button(this);
        bt.setText("Log in!");

        layout1.addView(bt);
        setContentView(layout1);
        *
        */

}
