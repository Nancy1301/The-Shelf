package com.example.android.theshelf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    Button button;
    Databasehelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.mainadmin);
        db = new Databasehelper(this);
        ArrayList<Admin> users = new ArrayList<>(db.getAllUsers());
        if (users.size() < 1) {
            db.addUser(101,"admin101","Nancy");
            db.addUser(102,"admin102","pogo");
        }
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Admin_Login.class);
                startActivity(i);

            }
        });

    }

}
