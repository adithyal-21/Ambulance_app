package com.example.Ambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    private EditText U1,P1,E1,D1,phone;
    private Button B1;
    String pass_exp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!])[A-Za-z\\d@$!]{8,}$";
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);
        U1 = (EditText) findViewById(R.id.signup_name);
        P1 = (EditText) findViewById(R.id.signup_pass);
        E1 = (EditText) findViewById(R.id.signup_email);
        D1 = (EditText) findViewById(R.id.signup_dob);
        phone = (EditText) findViewById(R.id.signup_phone);
        B1 = (Button) findViewById(R.id.signup_btn);

        DB = new DBHelper(this);
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = U1.getText().toString();
                String pass = P1.getText().toString();
                String email = E1.getText().toString();
                String phones = phone.getText().toString();
                String dob = D1.getText().toString();

                if (username.isEmpty()){
                    Toast.makeText(Signup.this,"Username Cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (pass.isEmpty()){
                    Toast.makeText(Signup.this,"Password Cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (email.isEmpty()){
                    Toast.makeText(Signup.this,"Email Cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (phones.isEmpty()){
                    Toast.makeText(Signup.this,"Phone Cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (dob.isEmpty()){
                    Toast.makeText(Signup.this,"DOB Cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(validate_password(pass)){
                    Boolean checkInsert = DB.insertUser(username, email, phones, dob, pass);
                    if (checkInsert) {
                        Toast.makeText(Signup.this, "Success : SignedUp", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Signup.this,Login.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Signup.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Signup.this, "Password should consist of uppercase, lowercase and symbols", Toast.LENGTH_SHORT).show();
                }




            }
//                Boolean checkInsert = DB.updateUser(username, "harshithkumar40@gmail.com", "1234567890", "15/7/2000", pass);
//                if (checkInsert) {
//                    Toast.makeText(getContext(), "done", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
//                }
//            }

//                Boolean checkInsert = DB.deleteUser("harshithkumar@gmail.com");
//                if (checkInsert) {
//                    Toast.makeText(getContext(), "done", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//                Toast.makeText(getContext(),"done",Toast.LENGTH_SHORT).show();
//                Cursor res = DB.getData();
//                if(res.getCount() == 0){
//                    Toast.makeText(getContext(),"Failed",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                StringBuffer buffer = new StringBuffer();
//                while (res.moveToNext()){
//                    buffer.append("Name :"+res.getString(0)+"\n");
//                }
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
//                alertDialogBuilder.setMessage(buffer.toString());
//
//                        AlertDialog alertDialog = alertDialogBuilder.create();
//                alertDialog.show();
//                }
        });

    }

    public boolean validate_password(String pass) {
        Pattern pattern = Pattern.compile(pass_exp);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }

}