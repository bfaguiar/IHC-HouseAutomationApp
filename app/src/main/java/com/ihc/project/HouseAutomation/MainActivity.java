package com.ihc.project.HouseAutomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.*;


public class MainActivity extends AppCompatActivity
 {

    private Button bt1, btForgotPassword, btCreateAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = (Button) findViewById(R.id.button_login);
        btForgotPassword = (Button) findViewById(R.id.button_forgotPassword);
        btCreateAccount = findViewById(R.id.button_createAccount);

        // set navitagtion bar color.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
            getWindow().setNavigationBarColor(android.support.v4.content.ContextCompat.getColor(this, R.color.colornavegationBarLogin));

        // launch home
        bt1.setOnClickListener( new Button.OnClickListener() {
            @Override public void onClick(View view) { launchHome(); }
        });

        // launch Forgot Password
        btForgotPassword.setOnClickListener( new Button.OnClickListener() {
            @Override public void onClick(View view) { launchForgotPassword(); }
        });

        btCreateAccount.setOnClickListener(new Button.OnClickListener() {
            @Override public void onClick(View view) { launchCreateAccount(); }
        });


    }

    private void launchHome() {
        startActivity(new Intent(this, DrawerHomeActivity.class));
    }

    private void launchForgotPassword() {
        startActivity(new Intent(this, forgotPasswordActivity.class));
    }

     private void launchCreateAccount() {
         startActivity(new Intent(this, CreateAccActivity.class));
     }



}
