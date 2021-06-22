package com.example.orderapp.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.session.PlaybackState;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.orderapp.database.DatabaseHelper;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TestAdapter {

    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;


    //Database column names
    public static final String COL_ORDER_ID = "OrderId";
    public static final String COL_DATE = "Date";
    public static final String COL_AMOUNT = "Amount";
    public static final String COL_DESCRIPTION = "Description";

    public static final int TABLE_ORDERS_LENGTH = 4;

    public TestAdapter(Context context) {
        this.mContext = context;
        mDbHelper = new DatabaseHelper(mContext);
    }

    //create the database
    public TestAdapter createDatabase() throws SQLException {
        try {
            mDbHelper.createDatabase();
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    //open database to read data
    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    public TestAdapter open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    //get data from database using raw queries
    public List<String> getData(){
        List<String> list = new ArrayList<>();
        Cursor cursor = mDb.rawQuery("SELECT * FROM Orders", null);
        cursor.moveToNext();
        list.add(COL_ORDER_ID);
        list.add(COL_DATE);
        list.add(COL_AMOUNT);
        list.add(COL_DESCRIPTION);

        while(!cursor.isAfterLast()){
            for (int i = 0; i < TABLE_ORDERS_LENGTH; i++){
                Log.i("ERROR ", "DATABASE ENTRIES: " + list.get(3));

                list.add(cursor.getString(i));
            }
            cursor.moveToNext();
        }

        cursor.close();
        return list;
    }

}