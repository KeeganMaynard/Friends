package com.keegan.android.friends;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "friendsDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FRIENDS = "friends";
    private static final String ID = "id";
    private static final String FNAME = "first_name";
    private static final String LNAME = "last_name";
    private static final String EMAIL = "email";

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String sqlCreate = "create table " + TABLE_FRIENDS + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + FNAME;
        sqlCreate += " text, " + LNAME + " text, " + EMAIL + " text)";

        db.execSQL(sqlCreate);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists " + TABLE_FRIENDS);
        onCreate(db);
    }

    public void insert(Friend friend)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_FRIENDS;
        sqlInsert += " values( null, '" + friend.getFirst_name();
        sqlInsert += "', '" + friend.getLast_name();
        sqlInsert += "', '" + friend.getEmail() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void deleteById(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_FRIENDS;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateById(int id, String fname, String lname, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlUpdate = "update " + TABLE_FRIENDS;
        sqlUpdate += " set " + FNAME + " = '" + fname + "', ";
        sqlUpdate += LNAME + " = '" + lname + "', ";
        sqlUpdate += EMAIL + " = '" + email + "' ";
        sqlUpdate += "where " + ID + " = " + id;

        db.execSQL(sqlUpdate);
        db.close();
    }

    public ArrayList<Friend> selectAll()
    {
        String sqlQuery = "select * from " + TABLE_FRIENDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Friend> friends = new ArrayList<>();
        while(cursor.moveToNext())
        {
            Friend currentFriend = new Friend(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3));
            friends.add(currentFriend);
        }
        db.close();
        return friends;
    }
}
