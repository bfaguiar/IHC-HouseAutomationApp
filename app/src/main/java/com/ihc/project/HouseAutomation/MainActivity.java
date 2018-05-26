package com.ihc.project.HouseAutomation;

import android.app.Activity;
import android.media.Image;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.view.*;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;


public class MainActivity extends AppCompatActivity
 {

    private Button bt1, btForgotPassword, btCreateAccount;
    private ImageView logoLog;
    private ArrayList<User> users;
    private TextInputLayout usernameForm, passwordForm;
    int validation = 0;

    String NAMEVALID;
    String USERVALID;

    TextInputEditText username, password;

     /**
      *
      */
     private static final long serialVersionUID = 1L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = new ArrayList<>();
        users.add( new User ("admin", "admin", "admin", "ihc@ua.pt"));

        bt1 = (Button) findViewById(R.id.button_login);
        btForgotPassword = (Button) findViewById(R.id.button_forgotPassword);
        btCreateAccount = findViewById(R.id.button_createAccount);

        username = findViewById(R.id.username_login_text);
        password = findViewById(R.id.password_login_text);
        logoLog     = findViewById(R.id.logo_login);

        usernameForm = findViewById(R.id.username_login);
        passwordForm = findViewById(R.id.password_login);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if(extras.getParcelableArrayList("users") != null)
                users.addAll( (ArrayList) extras.getParcelableArrayList("users"));

            for(User user : users)
                Log.i("TAG", user.toString());
        }

        Glide.with(this).load(R.drawable.houseautomation_logo_png).into(logoLog);

        // set navitagtion bar color.
            getWindow().setNavigationBarColor(android.support.v4.content.ContextCompat.getColor(this, R.color.colornavegationBarLogin));




                bt1.setOnClickListener( new Button.OnClickListener() {
                @Override public void onClick(View view) {
                    if( users != null) {
                        for (int i = 0; i < users.size(); i++)
                            if (users.get(i) != null)
                                if (username.getText().toString().equals(users.get(i).getUsername()) && password.getText().toString().equals(users.get(i).getPassword())) {
                                    validation = 1;
                                    NAMEVALID = users.get(i).getName();
                                    USERVALID = users.get(i).getUsername();
                                    Log.i("Tag"," OLA2: " + users.get(i).getUsername() +users.get(i).getName());
                                    break;
                                }
                    } else {
                        Toast.makeText(getApplicationContext(), "Create an Account First", Toast.LENGTH_SHORT).show();
                        Log.e("ola", "create account first");
                    }

                    if(validation == 1) {
                        usernameForm.setError(null);;
                        passwordForm.setError(null);;
                        launchHome();
                    }
                    else {
                        usernameForm.setError("Username or Password Invalid.");
                        passwordForm.setError("Error");
                    }
                }
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
        Intent intent = new Intent(MainActivity.this, DrawerHomeActivity.class);
        Bundle bundle = new Bundle();
        String[] str = {USERVALID, NAMEVALID};
        bundle.putStringArray("LoginData", str);
        intent.putExtras(bundle);
        Log.i("Tag"," OLA2: " + str[0] + str[1]);
        startActivity(intent);

    }

    private void launchForgotPassword() {
        startActivity(new Intent(this, forgotPasswordActivity.class));

    }

     private void launchCreateAccount() {
         startActivity(new Intent(this, CreateAccActivity.class));

     }



}
