package com.example.Ambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ambulance_display extends AppCompatActivity {
    private TableLayout t1;
    private TableRow tr;
    private TextView t10,t2,t3,t4,t5;
    AdminHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_display);
        DB = new AdminHelper(this);
        t1 = (TableLayout) findViewById(R.id.tt1);
        t1.setColumnStretchable(0,true);
        t1.setColumnStretchable(1,true);
        t1.setColumnStretchable(2,true);
        t1.setColumnStretchable(3,true);

        Cursor result = DB.get_ambulance();
        if(result.getCount() > 0){

            while (result.moveToNext()){
                tr = new TableRow(this);
                t10 = new TextView(this);
                t2 = new TextView(this);
                t3 = new TextView(this);
                t4 = new TextView(this);
                t5 = new Button(this);


                t5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String data = "7019821076";
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + data));
                        startActivity(intent);
                    }
                });
                t10.setText(result.getString(0));
                t10.setTextSize(11);


                t2.setText(result.getString(1));
                t2.setTextSize(11);

                t3.setText(result.getString(2));
                t3.setTextSize(11);

                t4.setText(result.getString(3));
                t4.setTextSize(11);

                t5.setText("☎");



                tr.addView(t10);

                tr.addView(t2);
                tr.addView(t3);
                tr.addView(t4);
                tr.addView(t5);
                t1.addView(tr);
            }
        }

    }
}