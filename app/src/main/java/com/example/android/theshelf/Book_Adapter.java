package com.example.android.theshelf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Book_Adapter extends ArrayAdapter
{
    public Book_Adapter(@NonNull Context context, ArrayList<Book> books) {
        super(context,0, books);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = (Book) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book, parent, false);
        }
        // Lookup view for data population
        TextView tvTitle = (TextView) convertView.findViewById(R.id.booklistname);
        TextView tvId = (TextView) convertView.findViewById(R.id.booklistbid);
        TextView tvA_Name = (TextView) convertView.findViewById(R.id.booklistaname);


        // Populate the data into the template view using the data object
        tvTitle.setText("Book name:- " + book.getTitle());
        tvId.setText("Book ID:- "+Integer.toString(book.getBookId()));
        tvA_Name.setText("Author Name:- "+book.getAuthor_Name());
        // Return the completed view to render on screen
        return convertView;

    }
}
