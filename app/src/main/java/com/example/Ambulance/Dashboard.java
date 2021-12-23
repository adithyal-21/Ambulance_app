package com.example.Ambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Ambulance.ui.home.HomeFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
private Button logout,profil,display;
private  TextView name1;

AdminHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dashboard);
        logout = (Button) findViewById(R.id.user_logout);
        display = (Button) findViewById(R.id.display);
        profil = (Button) findViewById(R.id.profile);
        DB = new AdminHelper(this);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name",null);
        String email = bundle.getString("email",null);
        String dob = bundle.getString("dob",null);
        String phone = bundle.getString("contact",null);
        name1= (TextView)findViewById(R.id.name1);
        name1.setText(name);

    display.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name",null);
        String email = bundle.getString("email",null);
        String dob = bundle.getString("dob",null);
        String phone = bundle.getString("contact",null);
        Intent intent = new Intent(Dashboard.this, ambulance_display.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
});

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              getIntent().removeExtra("name");
                getIntent().removeExtra("email");
                getIntent().removeExtra("dob");
                getIntent().removeExtra("contact");

                Toast.makeText(Dashboard.this,"Success : LoggedOut",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Dashboard.this, MainActivity.class);
                startActivity(intent);


            }
        });
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundles = new Bundle();
                bundles.putString("up_name",name);
                bundles.putString("up_email",email);
                bundles.putString("up_dob",dob);
                bundles.putString("up_phone",phone);


                Intent intent = new Intent(Dashboard.this,Profile.class);
                intent.putExtras(bundles);
                startActivity(intent);
            }
        });
    }
}