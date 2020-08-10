package com.example.android.theshelf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Add_Student extends Activity {
    EditText edtxt1,edtxt2;
    String student_name;
    int student_id;
    Button btn;
    private Databasehelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);
        edtxt1 = (EditText)findViewById(R.id.Add_student_name);
        edtxt2 = (EditText)findViewById(R.id.Add_student_id);

        btn = (Button)findViewById(R.id.addstdn);
        db = new Databasehelper(this);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String std = edtxt2.getText().toString();
                student_name = edtxt1.getText().toString();


                if(std.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Enter details", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    student_id = Integer.parseInt(std);
                }

                long insID = db.addstudent(student_id,Admin_Login.id,student_name);

                if (insID>0) {
                    Toast.makeText(getApplicationContext(), "User "+insID+" Added", Toast.LENGTH_LONG).show();
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Could not be added kindly add information correctly.", Toast.LENGTH_LONG).show();
                    finish();
                }
                Intent i = new Intent(Add_Student.this, Admin_Menu.class);
                startActivity(i);

            }
        });    }
}
