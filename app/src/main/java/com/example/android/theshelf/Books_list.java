package com.example.android.theshelf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Books_list extends Activity {
    ListView listView;
    Databasehelper db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.books_list);


        listView = (ListView) findViewById(R.id.listBook);

        db = new Databasehelper(this);
        ArrayList<Book> Books_list = new ArrayList<Book>();
        Books_list = db.getAllBookList();
        // Create the adapter to convert the array to views
        Book_Adapter adapter = new Book_Adapter(this, Books_list);
        // Attach the adapter to a ListView
        listView.setAdapter(adapter);
    }
}
