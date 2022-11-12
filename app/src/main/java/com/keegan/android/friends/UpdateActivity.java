package com.keegan.android.friends;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    DatabaseManager dbManager;

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        dbManager = new DatabaseManager( this );
        updateView( );
    }

    // Build a View dynamically with all the candies
    public void updateView( ) {
        ArrayList<Friend> friends = dbManager.selectAll( );

        // create ScrollView and GridLayout
        if( friends.size( ) > 0 ) {

            ScrollView scrollView = new ScrollView( this );
            GridLayout grid = new GridLayout( this );
            grid.setRowCount( friends.size( ) );
            grid.setColumnCount( 4 );

            // create arrays of components
            TextView [] ids = new TextView[friends.size( )];
            EditText [][] namesAndEmail = new EditText[friends.size( )][3];
            Button [] buttons = new Button[friends.size( )];
            ButtonHandler bh = new ButtonHandler( );

            // retrieve width of screen
            Point size = new Point( );
            getWindowManager( ).getDefaultDisplay( ).getSize( size );
            int width = size.x;

            // create the TextView for the candy's id
            int i = 0;

            for ( Friend friend : friends ) {

                ids[i] = new TextView( this );
                ids[i].setGravity( Gravity.CENTER );
                ids[i].setText( "" + friend.getId( ) );

                // create the three EditTexts for the friend's names and email
                namesAndEmail[i][0] = new EditText( this );
                namesAndEmail[i][1] = new EditText( this );
                namesAndEmail[i][2] = new EditText(this);
                namesAndEmail[i][0].setText( friend.getFirst_name());
                namesAndEmail[i][1].setText( friend.getLast_name() );
                namesAndEmail[i][2].setText(friend.getEmail());
                namesAndEmail[i][0].setId( 10 * friend.getId( ) );
                namesAndEmail[i][1].setId( 10 * friend.getId( ) + 1 );
                namesAndEmail[i][2].setId(10 * friend.getId() + 2);

                // create the button
                buttons[i] = new Button( this );
                buttons[i].setText( "Update" );
                buttons[i].setId( friend.getId( ) );

                // set up event handling
                buttons[i].setOnClickListener( bh );

                // add the elements to grid
                grid.addView( ids[i], width / 10,
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndEmail[i][0], ( int ) ( width * .4 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndEmail[i][1], ( int ) ( width * .15 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( buttons[i], ( int ) ( width * .35 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );

                i++;
            }
            scrollView.addView( grid );
            setContentView( scrollView );
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick( View v ) {
            // retrieve names and email
            int friendId = v.getId( );
            EditText fnameET = findViewById( 10 * friendId );
            EditText lnameET = findViewById( 10 * friendId + 1 );
            EditText emailET = findViewById(10 * friendId + 2);
            String fname = fnameET.getText( ).toString( );
            String lname = lnameET.getText( ).toString( );
            String email = emailET.getText().toString();

            // update candy in database
            try {

                dbManager.updateById( friendId, fname, lname, email);
                Toast.makeText( UpdateActivity.this, "Friend updated", Toast.LENGTH_SHORT ).show( );

                // update screen
                updateView( );
            } catch( NumberFormatException nfe ) {
                Toast.makeText( UpdateActivity.this, "Friend error", Toast.LENGTH_LONG ).show( );
            }
        }
    }
}
