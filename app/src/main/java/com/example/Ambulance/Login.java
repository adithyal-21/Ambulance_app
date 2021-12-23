package com.example.Ambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Ambulance.ui.home.HomeFragment;

public class Login extends AppCompatActivity {
private EditText user_login, user_pass;
private Button login_btn;
DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        user_login = (EditText) findViewById(R.id.login_user);
        user_pass = (EditText) findViewById(R.id.login_pass);
        login_btn = (Button) findViewById(R.id.login_btn);
        DB = new DBHelper(this);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = user_login.getText().toString();
                String password = user_pass.getText().toString();
                if (email.isEmpty()){
                    Toast.makeText(Login.this,"Email Cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (password.isEmpty()){
                    Toast.makeText(Login.this,"Password Cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    boolean check_user = DB.checkUser(email,password);
                    if(check_user){
                        Cursor cursor = DB.Individual_data(email);

                        Bundle bundle = new Bundle();
                        while(cursor.moveToNext()) {
                            bundle.putString("email", email);
                            bundle.putString("name", cursor.getString(0));
                            bundle.putString("contact", cursor.getString(2));
                            bundle.putString("dob", cursor.getString(3));
                        }
                        Toast.makeText(Login.this,"Success:LoggedIn",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Dashboard.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
                    }
                }



                }

        });
    }
}