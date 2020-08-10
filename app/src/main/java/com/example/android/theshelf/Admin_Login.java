package com.example.android.theshelf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Admin_Login extends Activity {
    EditText edttext1,edttxt2;
    static int id;
    String password;
    Button btnlogin;
    Databasehelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);
        edttext1 = findViewById(R.id.Admin_Login_Id);
        edttxt2 = findViewById(R.id.Admin_Login_Password);
        btnlogin = findViewById(R.id.AdminLogin);
        db = new Databasehelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String val = edttext1.getText().toString();
                id = Integer.parseInt(val);
                password = edttxt2.getText().toString();
                String pass = db.getPassword(id);

                if (password.equals(pass))
                {
                    Intent i = new Intent(Admin_Login.this, Admin_Menu.class);
                    startActivity(i);

                    Toast.makeText(getApplicationContext(), "Correct Password", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Wrong User ID and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




}

