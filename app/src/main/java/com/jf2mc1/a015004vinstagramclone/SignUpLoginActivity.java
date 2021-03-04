package com.jf2mc1.a015004vinstagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtSuUname, edtSuPword, edtLiUname, edtLiPword;
    private Button btnSignup, btnLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        edtLiPword = findViewById(R.id.edt_li_pword);
        edtLiUname = findViewById(R.id.edt_li_uname);
        edtSuPword = findViewById(R.id.edt_su_pword);
        edtSuUname = findViewById(R.id.edt_su_uname);

        btnSignup = findViewById(R.id.btn_signup);
        btnLogin = findViewById(R.id.btn_login);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create new Parse user
                ParseUser appUser = new ParseUser();
                appUser.setUsername(edtSuUname.getText().toString());
                appUser.setPassword(edtSuPword.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(SignUpLoginActivity.this,
                                    appUser.getUsername() + " is signed up :)",
                                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        } else {
                            FancyToast.makeText(SignUpLoginActivity.this,
                                    e.getMessage() + ":(",
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(edtLiUname.getText().toString(),
                        edtLiPword.getText().toString(), new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if (user != null && e == null) {
                                    FancyToast.makeText(SignUpLoginActivity.this,
                                            user.getUsername() + " is logged in :)",
                                            FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                } else {
                                    FancyToast.makeText(SignUpLoginActivity.this,
                                            e.getMessage() + ": user details not known :(",
                                            FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                                }
                            }
                        });


            }
        });


    }
}


