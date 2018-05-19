package com.ihc.project.HouseAutomation;

import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.view.*;
import android.widget.Toast;

public class forgotPasswordActivity extends AppCompatActivity {
    private Button bt;
    CharSequence email;
    TextInputLayout email_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // set navitagtion bar color.
        //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
            getWindow().setNavigationBarColor(android.support.v4.content.ContextCompat.getColor(this, R.color.colornavegationBarLogin));

        bt =  findViewById(R.id.mail_submit);
        email_input =  findViewById(R.id.input_email);
        email = email_input.getEditText().getText();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (isValidEmail(email)) {
                    email_input.setError(null);
                    Toast.makeText(getApplicationContext(), "Password was sent.", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            forgotPasswordActivity.this.finish();
                        }
                    }, 1000);
                } else
                    validateEmail();
            }
        });

    }

    private boolean validateEmail() {

        String email = email_input.getEditText().getText().toString().trim();
        if (!isValidEmail(email)) {
            email_input.setError("Enter a valid E-mail.");
            return false;
        } else if (email.isEmpty()) {
            email_input.setError(null);
            return false;
        } else {
            email_input.setError(null);
            return true;
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
