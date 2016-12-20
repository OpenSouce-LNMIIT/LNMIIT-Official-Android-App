package lnmiit.android.app.utilities;

/**
 * Created by dexter on 9/12/16.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import lnmiit.android.app.model.UpdateDetail;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "lnmiit_database";

    // Contacts table name
    private static final String TABLE_UPDATE = "_update";
    private static final String TABLE_NEWS = "_news";

    // Contacts Table Columns names
    private static final String KEY_ID = "id" ;
    private static final String KEY_TITLE = "title";
    private static final String KEY_LINK = "link";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_UPDATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_UPDATE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_LINK + " TEXT" + ")";
        String CREATE_NEWS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NEWS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TITLE + " TEXT,"
                + KEY_LINK + " TEXT" + ");";
        db.execSQL(CREATE_UPDATE_TABLE);
        db.execSQL(CREATE_NEWS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UPDATE);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new item to update table
    public void addItemtoUpdate(UpdateDetail item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, item.getTitle()); // Contact Name
        values.put(KEY_LINK, item.getUrl()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_UPDATE, null, values);
        db.close(); // Closing database connection
    }

    // Adding new item to update table
    public void addItemtoNews(UpdateDetail item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, item.getTitle()); // Contact Name
        values.put(KEY_LINK, item.getUrl()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_NEWS, null, values);
        db.close(); // Closing database connection
    }


    // Getting All Updates
    public List<UpdateDetail> getAllUpdates() {
        List<UpdateDetail> updateList = new ArrayList<UpdateDetail>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_UPDATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UpdateDetail contact = new UpdateDetail();
                contact.setTitle(cursor.getString(1));
                contact.setUrl(cursor.getString(2));
                updateList.add(contact);
            } while (cursor.moveToNext());
        }

        db.close();
        return updateList;
    }

    // Getting All Updates
    public List<UpdateDetail> getAllNews() {
        List<UpdateDetail> updateList = new ArrayList<UpdateDetail>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NEWS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UpdateDetail contact = new UpdateDetail();
                contact.setTitle(cursor.getString(1));
                contact.setUrl(cursor.getString(2));
                updateList.add(contact);
            } while (cursor.moveToNext());
        }

        db.close();
        return updateList;
    }

    public boolean hasObjectInUpdate(String title) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + TABLE_UPDATE + " WHERE " + KEY_TITLE + " =?";

        // Add the String you are searching by here.
        // Put it in an array to avoid an unrecognized token error
        Cursor cursor = db.rawQuery(selectString, new String[] {title});

        boolean hasObject = false;
        if(cursor.moveToFirst()){
            hasObject = true;
        }

        cursor.close();          // Dont forget to close your cursor
        db.close();              //AND your Database!
        return hasObject;
    }

    public boolean hasObjectInNews(String title) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + TABLE_NEWS + " WHERE " + KEY_TITLE + " =?";

        // Add the String you are searching by here.
        // Put it in an array to avoid an unrecognized token error
        Cursor cursor = db.rawQuery(selectString, new String[] {title});

        boolean hasObject = false;
        if(cursor.moveToFirst()){
            hasObject = true;
        }

        cursor.close();          // Dont forget to close your cursor
        db.close();              //AND your Database!
        return hasObject;
    }
}
