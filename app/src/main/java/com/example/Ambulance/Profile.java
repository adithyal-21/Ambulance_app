package com.example.Ambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
private EditText upName,upEmail,upPhone,upDob;
private Button updat;
DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        upName = (EditText) findViewById(R.id.up_name);
        upEmail = (EditText) findViewById(R.id.up_email);
        upDob = (EditText) findViewById(R.id.up_date);
        upPhone = (EditText) findViewById(R.id.up_phone);
        updat = (Button) findViewById(R.id.update);

      upEmail.setEnabled(false);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("up_name","Name");
        String email = bundle.getString("up_email","Email");
        String phone = bundle.getString("up_phone","Phone");
        String dob = bundle.getString("up_dob","DOB");

        upName.setText(name);
        upEmail.setText(email);
        upPhone.setText(phone);
        upDob.setText(dob);



DB = new DBHelper(this);
        updat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = upName.getText().toString();

                String emails = upEmail.getText().toString();
                String phones = upPhone.getText().toString();
                String dobs = upDob.getText().toString();
                if (username.isEmpty()){
                    Toast.makeText(Profile.this,"Username Cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }

                else if (emails.isEmpty()){
                    Toast.makeText(Profile.this,"Email Cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (phones.isEmpty()){
                    Toast.makeText(Profile.this,"Phone Cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (dobs.isEmpty()){
                    Toast.makeText(Profile.this,"DOB Cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    boolean update_user = DB.updateUser(username,emails,phones,dobs);
                    if (update_user){
                        Toast.makeText(Profile.this,"Success : Updated",Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(Profile.this, "Error : Failed to Update", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
    }
}