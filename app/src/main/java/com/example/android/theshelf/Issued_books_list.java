package com.example.android.theshelf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Issued_books_list extends Activity {
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.issued_books_list );
        setTitle( "Issued Book List" );
        btn1 = (Button) findViewById( R.id.back );
        btn1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent( Issued_books_list.this,Admin_Menu.class);
            }
        } );
    }

}

