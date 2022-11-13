package com.keegan.android.friends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ScrollView;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    private ScrollView scrollView;
    private int buttonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbManager = new DatabaseManager(this);
        scrollView = findViewById(R.id.scrollView);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        buttonWidth = size.x;
        updateView();
    }

    protected void onResume()
    {
        super.onResume();
        updateView();
    }


    public void updateView()
    {
        ArrayList<Friend> friends = dbManager.selectAll();
        if(friends.size() > 0)
        {
            scrollView.removeAllViewsInLayout();

            GridLayout grid = new GridLayout(this);
            grid.setRowCount(friends.size());
            grid.setColumnCount(3);

            FriendButton[] buttons  = new FriendButton[friends.size()];

            int i = 0;
            for(Friend friend : friends)
            {
                buttons[i] = new FriendButton(this, friend);
                String toString = friend.getFirst_name() + " " + friend.getLast_name() + "\n"
                        + friend.getEmail();
                buttons[i].setText(toString);

                grid.addView(buttons[i], buttonWidth, GridLayout.LayoutParams.WRAP_CONTENT);
                i++;
            }
            scrollView.addView(grid);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId( );
        switch ( id ) {
            case R.id.action_add:
                Intent insertIntent = new Intent( this, InsertActivity.class );
                this.startActivity( insertIntent );
                return true;
            case R.id.action_delete:
                Intent deleteIntent = new Intent( this, DeleteActivity.class );
                this.startActivity( deleteIntent );
                return true;
            case R.id.action_update:
                Intent updateIntent = new Intent( this, UpdateActivity.class );
                this.startActivity( updateIntent );
                return true;
            case R.id.action_exit:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected( item );
        }
    }
}