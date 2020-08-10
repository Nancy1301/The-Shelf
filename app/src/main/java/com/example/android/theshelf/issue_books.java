package com.example.android.theshelf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class issue_books extends Activity
{
    EditText edittxt1;
    EditText edittxt2;
    Button btn,btn1;

    private Databasehelper db;
    TextView txt,txtr;
    int sid,bid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.issue_book);
        edittxt1 = (EditText)findViewById(R.id.Add_stu_id);
        edittxt2 = (EditText)findViewById(R.id.Add_bk_id);
        txt = (TextView)findViewById( R.id.issue_date );
        txtr = (TextView)findViewById( R.id.return_date );
        btn1 = (Button)findViewById( R.id.done );

        btn = (Button)findViewById(R.id.issue);
        db = new Databasehelper(this);
        btn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String st = edittxt1.getText().toString();
                String st1 =edittxt2.getText().toString();

                if(st.matches("") || st1.matches( "" ))
                {
                    Toast.makeText(getApplicationContext(), "Enter details", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    sid  = Integer.parseInt(st);
                    bid = Integer.parseInt(st1);
                }
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                String issue = dateFormat.format(date);
                String value1 = "Issue Date is :- " + issue;
                txt.setText(value1);
                cal.add(Calendar.DATE, 15);
                String return1 = dateFormat.format(cal.getTime());
                String result = "Return Date is :- " + return1;
                txtr.setText(result);
                if (db.Icheckbook(bid) > 0)
                {
                    Toast.makeText(getApplicationContext(), "Book is already issued", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    long insID = db.issuebook( sid, bid, Admin_Login.id, issue, return1 );
                    Toast.makeText(getApplicationContext(), "Book issued", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn1.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent i = new Intent(issue_books.this, Admin_Menu.class);
                startActivity(i);


            }
        });

    }
}
