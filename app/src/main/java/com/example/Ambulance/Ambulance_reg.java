package com.example.Ambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Ambulance_reg extends AppCompatActivity {
    private EditText name,type,phone,loc;
    private Button add,logout,move;
    AdminHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ambulance_reg);
        name = (EditText) findViewById(R.id.am_name);
        type = (EditText) findViewById(R.id.am_type);
        phone = (EditText) findViewById(R.id.am_phone);
        loc = (EditText) findViewById(R.id.am_loc);
        add = (Button) findViewById(R.id.am_btn);
        DB = new AdminHelper(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String names = name.getText().toString();
                String types = type.getText().toString();
                String phones = phone.getText().toString();
                String locs = loc.getText().toString();

                if(names.isEmpty()){
                    Toast.makeText(Ambulance_reg.this,"Error : Name cannot be Empty",Toast.LENGTH_SHORT).show();
                }else if(types.isEmpty()){
                    Toast.makeText(Ambulance_reg.this,"Error : Type cannot be Empty",Toast.LENGTH_SHORT).show();
                }else if(phones.isEmpty()){
                    Toast.makeText(Ambulance_reg.this,"Error : Phone cannot be Empty",Toast.LENGTH_SHORT).show();
                }
                else if(locs.isEmpty()){
                    Toast.makeText(Ambulance_reg.this,"Error : Location cannot be Empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean insert_amb = DB.add_ambulance(names,types,phones,locs);
                    if(insert_amb){
                        Toast.makeText(Ambulance_reg.this,"Success : Data Inserted",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Ambulance_reg.this,"Error : Failed to Add",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}