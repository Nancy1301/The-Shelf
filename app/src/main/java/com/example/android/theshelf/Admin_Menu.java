package com.example.android.theshelf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Admin_Menu extends Activity {
    String id;
    TextView txt;
    Button addstdnt;
    Button addbook;
    Button issuebook;
    Button issuedbooklist;
    Button booklist;
    Button returnbooks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_menu);
        addstdnt = findViewById(R.id.mainadd_student);
        addbook = findViewById(R.id.mainadd_books);
        issuebook = findViewById(R.id.mainissue_books);
        booklist = findViewById( R.id.mainlist_of_books );
        returnbooks = findViewById( R.id.mainreturn_books);
        addstdnt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin_Menu.this, Add_Student.class);
                startActivity(i);
            }

        });
        addbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin_Menu.this, Add_books.class);
                startActivity(i);
            }
        });
        issuebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin_Menu.this, issue_books.class);
                startActivity(i);
            }
        });

        booklist.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin_Menu.this, Books_list.class);
                startActivity( i );
            }
        } );
        returnbooks.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( Admin_Menu.this, Return_books.class);
                startActivity(i);
            }
        } );

    }
}