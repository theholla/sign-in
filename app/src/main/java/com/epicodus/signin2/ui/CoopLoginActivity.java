package com.epicodus.signin2.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.signin2.R;

public class CoopLoginActivity extends AppCompatActivity {

    private TextView mMainHeaderTextView;
    private SharedPreferences mPreferences;
    private EditText mCoopEmail, mCoopPassword;
    private Button mCoopSignInButton, mCoopCreateAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainHeaderTextView = (TextView) findViewById(R.id.mainHeaderTextView);
        mPreferences = getApplicationContext().getSharedPreferences("signinapp", Context.MODE_PRIVATE);
        mCoopEmail = (EditText) findViewById(R.id.coopEmailEditText);
        mCoopPassword = (EditText) findViewById(R.id.coopPasswordEditText);
        mCoopSignInButton = (Button) findViewById(R.id.coopSignInButton);
        mCoopCreateAccountButton = (Button) findViewById(R.id.coopCreateAccountButton);

        mCoopSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coopEmail = mCoopEmail.getText().toString().trim();
                String coopPassword = mCoopPassword.getText().toString().trim();
                BikeCollective bikeCollective = BikeCollective.find(coopEmail);

                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putString("coopEmail", coopEmail);
                editor.commit();

                if (bikeCollective != null) {
                    if (bikeCollective.getPassword().equals(coopPassword)) {
                        Intent intent = new Intent(CoopLoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}