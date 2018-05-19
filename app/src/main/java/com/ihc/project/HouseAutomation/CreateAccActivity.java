package com.ihc.project.HouseAutomation;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccActivity extends AppCompatActivity {
    Button bt;
    CharSequence email, username, password, confirm_password;
    EditText email_input, name_input, password_input, confirm_password_input, username_input;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);


        // set navitagtion bar color.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
            getWindow().setNavigationBarColor(android.support.v4.content.ContextCompat.getColor(this, R.color.colornavegationBarLogin));


        bt = findViewById(R.id.CAButton);
        email_input =  findViewById(R.id.CAMail);
        email = email_input.getText();
        password_input =  findViewById(R.id.CAPass);
        password = password_input.getText();
        confirm_password_input = findViewById(R.id.CACPass);
        confirm_password = confirm_password_input.getText();
        name_input =  findViewById(R.id.CAName);
        name = name_input.getText().toString();
        username_input =  findViewById(R.id.CAUsername);
        username = username_input.getText();


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isValidEmail(email)) {
                    Log.println(Log.ASSERT,"my Tag", "isValidMail FALSE");
                    email_input.setError("Invalid Email ID");

                }
                /*
                else if(!isValidName(name)) {
                    Log.println(Log.ASSERT,"my Tag", "isValidName FALSE");
                    Toast.makeText(getApplicationContext(), "Invalid Name!", Toast.LENGTH_SHORT).show();
                }
                */
                else if(!TextUtils.equals(password,confirm_password)){
                    Log.println(Log.ASSERT,"my Tag", "isValidPass FALSE");
                    Toast.makeText(getApplicationContext(), "Passwords don't match!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.println(Log.ASSERT,"my Tag", "ACCOUNT CREATED");
                    Toast.makeText(getApplicationContext(),"Account created!",Toast.LENGTH_SHORT).show();

                    Handler mHandler = new Handler();
                    mHandler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            launchMain();
                        }

                    }, 1000);

                }
            }
        });




    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isValidName(String name){
        return (name.matches("[A-Za-z]+$"));
    }

    private void launchMain() {

        startActivity(new Intent(this, MainActivity.class));

    }
}
