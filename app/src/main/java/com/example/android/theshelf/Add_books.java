package com.example.android.theshelf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Add_books extends Activity
{
    EditText edtxt1,edtxt2,edtxt3;
    String books_name;
    int books_id;
    String author_name;
    Button btn;
    private Databasehelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_books);
        edtxt1 = (EditText)findViewById(R.id.Add_Book_names);
        edtxt2 = (EditText)findViewById(R.id.Add_book_id);
        edtxt3=(EditText)findViewById(R.id.Add_author_name);

        btn = (Button)findViewById(R.id.addbookz);
        db = new Databasehelper(this);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String std = edtxt2.getText().toString();
                books_name = edtxt1.getText().toString();
                author_name = edtxt3.getText().toString();

                if(std.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Enter details", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    books_id = Integer.parseInt(std);
                }
                db.addbook(books_id,Admin_Login.id,books_name,author_name);
                Intent i = new Intent(Add_books.this, Admin_Menu.class);
                Toast.makeText(getApplicationContext(), "Book Added!", Toast.LENGTH_SHORT).show();
                startActivity(i);

            }
        });    }


    public String setbooks_name(String b_name)
    {
        return books_name;
    }

    public int setbooks_id(int b_id)
    {
        return books_id;
    }

    public String setauthor_name(String author_name)
    {
        return author_name;
    }

}
