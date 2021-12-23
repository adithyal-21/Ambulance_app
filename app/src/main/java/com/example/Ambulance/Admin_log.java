package com.example.Ambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;

public class Admin_log extends AppCompatActivity {
    private EditText email,pass;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_log);
        email = (EditText)findViewById(R.id.a1);
        pass = (EditText)findViewById(R.id.a2);
        login = (Button)findViewById(R.id.a_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_data = email.getText().toString();
                String pass_data = pass.getText().toString();
                if(email_data.isEmpty()){
                    Toast.makeText(Admin_log.this,"Email Cannot be Empty",Toast.LENGTH_SHORT).show();
                }
                else if(pass_data.isEmpty()){
                    Toast.makeText(Admin_log.this,"Password Cannot be Empty",Toast.LENGTH_SHORT).show();
                }
                else if (email_data.equals("admin@gmail.com") && pass_data.equals("admin")){
                    Toast.makeText(Admin_log.this,"Success:LoggedIn",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Admin_log.this, Admin_Dashboard.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Admin_log.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}