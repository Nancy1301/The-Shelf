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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Return_books extends Activity {
    Databasehelper db;
    Button Search1,Search2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.return_books);
        db = new Databasehelper(this);
        final EditText editTextbid = (EditText) findViewById(R.id.Add_bk_id);

        Search1 = (Button) findViewById(R.id.rbook);
        Search2 = (Button) findViewById(R.id.done);


        Search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editTextbid.getText().toString();
                final TextView txt = (TextView) findViewById(R.id.fine);
                if (value.matches("")) {
                    Toast.makeText(getApplicationContext(), "enter book id", Toast.LENGTH_SHORT).show();
                    return;
                }
                int val = 0;
                val = Integer.parseInt(value);
                int b_id = db.getBookID(val);
                if(b_id>0) {

                    if (db.Icheckbook(b_id) > 0)
                    {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        Date date2 = new Date();
                        Calendar cal = Calendar.getInstance();
                        String cur = dateFormat.format(date2);
                        String Return_Date = db.getReturnDate(b_id);

                        try {
                            Date date = dateFormat.parse(cur);

                            Date date1 = dateFormat.parse(Return_Date);
                            long difference = date.getTime() - date1.getTime();
                            long differenceDates = difference / (24 * 60 * 60 * 1000);


                            if(differenceDates > 0 )
                            {
                                long fine = differenceDates*5;
                                txt.setText("Fine is :- " + fine);
                                if(db.deleteTitle(b_id))
                                {
                                    Toast.makeText(getApplicationContext(), "Book is returned successfully", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                txt.setText("No Fine" );
                                if (db.deleteTitle(b_id))
                                {
                                    Toast.makeText(getApplicationContext(), "Book is returned successfully", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        catch (ParseException e) {
                            e.printStackTrace();
                        }



                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "This Book is not issued", Toast.LENGTH_SHORT).show();


                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "The Book Does Not Exist", Toast.LENGTH_SHORT).show();

                }



            }
        });
        Search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(Return_books.this, Admin_Menu.class);
                startActivity( i1 );
            }
        });

    }



}
