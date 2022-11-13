package com.keegan.android.friends;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        dbManager = new DatabaseManager( this );
        setContentView( R.layout.activity_insert );
    }

    public void insert( View v ) {
        // Retrieve name and price
        EditText fnameEditText = findViewById( R.id.input_first_name );
        EditText lnameEditText = findViewById( R.id.input_last_name );
        EditText emailEditText = findViewById(R.id.input_email);
        String first = fnameEditText.getText( ).toString( );
        String last = lnameEditText.getText( ).toString( );
        String email = emailEditText.getText().toString();

        // insert new friend in database
        try {
            Friend friend = new Friend(0, first, last, email);
            dbManager.insert(friend);
            Toast.makeText( this, "Friend added", Toast.LENGTH_SHORT ).show( );
        } catch( NumberFormatException nfe ) {
            Toast.makeText( this, "Friend error", Toast.LENGTH_LONG ).show( );
        }

        // clear data
        fnameEditText.setText( "" );
        lnameEditText.setText( "" );
        emailEditText.setText("");
    }

    public void goBack( View v ) {
        this.finish( );
    }
}
