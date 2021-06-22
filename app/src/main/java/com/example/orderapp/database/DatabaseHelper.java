package com.example.orderapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.orderapp.models.UserModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "orderDb.db";
    private static String DB_PATH =  "/data/data/com.example.orderapp/databases/orderDb.db";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;

        if (!checkDataBase()){
            copyDataBase();
        }
        mDataBase = this.getWritableDatabase();
    }

    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    private boolean checkDataBase() {
        File db = new File(mContext.getDatabasePath(DB_NAME).getPath()); //Get the file name of the database
        Log.d("DBPATH","DB Path is " + db.getPath());
        if (db.exists()){
            Log.i("ERROR", "Database exists!");
            return true; // If it exists then return doing nothing
        }

        // Get the parent (directory in which the database file would be)
        File dbdir = db.getParentFile();
        // If the directory does not exist then make the directory (and higher level directories)
        if (!dbdir.exists()) {
            db.getParentFile().mkdirs();
            dbdir.mkdirs();
            Log.i("ERROR", "Database does not exist!");
        }
        return false;
    }

    private void copyDataBase() {
        try {
            InputStream myInput = mContext.getAssets().open(DB_NAME);
            String outputFileName = mContext.getDatabasePath(DB_NAME).getPath();
            Log.d("LIFECYCLE", outputFileName);
            OutputStream myOutput = new FileOutputStream(outputFileName);

            byte[] buffer = new byte[1024];
            int length;
            while( (length=myInput.read(buffer)) > 0 ){
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
            Log.i("ERROR", "Database copied!");

        } catch ( IOException e) {
            Log.i("ERROR", "Error while copying database!");
            e.printStackTrace();
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        //InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public void createDatabase() throws IOException {
        // If the database exist, copy it from the assets.
        boolean mDataBaseExist = checkDataBase();
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            // Copy the database from assests
            copyDataBase();
            Log.e(TAG, "createDatabase database created");
        }
    }

    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }

    //Verify if a user is in the database
    public boolean userExists(String accountName, String password){
        String[] columns = {"UserName"};
        mDataBase=SQLiteDatabase.openDatabase(DB_PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);

        String selection = "UserName=? and Password=?";
        String[] selectionArgs = {accountName, password};

        //query the database with user input
        Cursor cursor = mDataBase.query("Users", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        close();
        mDataBase.close();

        if(count > 0){
            return true;
        } else {
            return false;
        }
    }

    //get user data from database and return them in a user object
    public UserModel getUser(String accountName, String password){

        if (!userExists(accountName, password)){
            return null;
        }
        else {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery( "select * from Users where UserName = " + "UserName", null );
            cursor.moveToFirst();
            Log.i("ERROR", "Password: ");

            int c = cursor.getColumnCount();
            String pass = cursor.getString(cursor.getColumnIndex("Password"));
            Log.i("ERROR", "Password: " + pass);


            String fName =  cursor.getString(cursor.getColumnIndex("FirstName"));
            Log.i("ERROR", "FName: " + fName);

            String lName = cursor.getString(cursor.getColumnIndex("LastName"));
            Log.i("ERROR", "LName: " + lName);

            String idString =  cursor.getString(cursor.getColumnIndex("Id"));
            int id = Integer.parseInt(idString);
            db.close();

            UserModel userModel = new UserModel(id, lName, fName, accountName, pass);
            if (password.compareTo(pass) == 0){
                //password matches
                return userModel;
            }
            else {
                return null;
            }


        }
    }

    //use bundled data to insert them in our database
    public void addEntry(String orderDate, String orderAmount, String orderDescription){
        ContentValues contentValues = new ContentValues();

        contentValues.put("OrderDate", orderDate);
        contentValues.put("Amount", orderAmount);
        contentValues.put("Description", orderDescription);

        mDataBase.insert("Orders", null, contentValues);

    }
}