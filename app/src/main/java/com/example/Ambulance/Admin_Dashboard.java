package com.example.Ambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class Admin_Dashboard extends AppCompatActivity {

    private Button logout,move;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_dashboard);
        logout = (Button) findViewById(R.id.adm_logout);
        move = (Button) findViewById(R.id.move);


        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Dashboard.this, Ambulance_reg.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Admin_Dashboard.this,MainActivity.class);
                       startActivity(intent);


            }
        });
    }
}