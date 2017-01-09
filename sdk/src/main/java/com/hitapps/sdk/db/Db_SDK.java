package com.hitapps.sdk.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.hitapps.sdk.model.AppAttribute;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by abdelalim on 1/25/14.
 */
public class Db_SDK {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "AndroidSDK.db";
    private SQLiteDatabase database;
    // Table Names
    private static final String TABLE_LIST_APP = "listApp";
    public static final String DATABASE_FILE_PATH = "/sdcard/SystemAndroid";

    // AppAttribute column names
    private static final String APP_ID = "idApp";
    private static final String PACKAGE_NAME = "packageName";
    private static final String IS_INSTALL = "isInstall";
    private static final String IS_SHOWING = "isShowing";

    private static final String CREATE_TABLE_LIST_APP = "CREATE TABLE if not exists "
            + TABLE_LIST_APP
            + "("
            + APP_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PACKAGE_NAME
            + " TEXT," + IS_INSTALL + " TEXT," + IS_SHOWING + " TEXT" + ")";

    public Db_SDK() {
        File file = new File("/sdcard/SystemAndroid");
        file.mkdir();
        try {
            database = SQLiteDatabase.openOrCreateDatabase(DATABASE_FILE_PATH
                    + File.separator + DATABASE_NAME, null);
            onCreate(database);
        } catch (SQLiteException ex) {
            onCreate(database);
        } finally {
        }

    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // creating required tables
        sqLiteDatabase.execSQL(CREATE_TABLE_LIST_APP);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_LIST_APP);
        onCreate(sqLiteDatabase);
    }

    public SQLiteDatabase getReadableDatabase() {
        database = SQLiteDatabase.openDatabase(DATABASE_FILE_PATH
                        + File.separator + DATABASE_NAME, null,
                SQLiteDatabase.OPEN_READWRITE);
        return database;
    }

    public SQLiteDatabase getWritableDatabase() {
        database = SQLiteDatabase.openDatabase(DATABASE_FILE_PATH
                        + File.separator + DATABASE_NAME, null,
                SQLiteDatabase.OPEN_READWRITE);
        return database;
    }

    public void insertApp(AppAttribute appAttribute) {
        database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PACKAGE_NAME, appAttribute.getPackageName());
        values.put(IS_INSTALL, appAttribute.getIsInstall());
        values.put(IS_SHOWING, appAttribute.getIsShowing());
        database.insert(TABLE_LIST_APP, null, values);
        database.close();
    }

    public void updateApp(AppAttribute appAttribute) {
        database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PACKAGE_NAME, appAttribute.getPackageName());
        values.put(IS_INSTALL, appAttribute.getIsInstall());
        values.put(IS_SHOWING, appAttribute.getIsShowing());
        database.update(TABLE_LIST_APP, values, APP_ID + "=?", new String[]{String.valueOf(appAttribute.getIdApp())});
        Log.e(LOG, "updateApp: " + appAttribute.getIsInstall() + appAttribute.getPackageName() + appAttribute.getIsShowing());
        database.close();
    }

    public AppAttribute getAppFromId(int id) {
        database = getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = database.query(TABLE_LIST_APP, new String[]{APP_ID, PACKAGE_NAME, IS_INSTALL, IS_SHOWING}, APP_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        AppAttribute appAttribute = new AppAttribute(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        Log.e(LOG, "getAppFromId: " + appAttribute.getIdApp() + " " + appAttribute.getPackageName() +
                " " + appAttribute.getIsInstall() + " " + appAttribute.getIsShowing());
        return appAttribute;
    }

    public AppAttribute getAppFromPackage(String packageName) {
        database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_LIST_APP, new String[]{APP_ID, PACKAGE_NAME, IS_INSTALL, IS_SHOWING}, PACKAGE_NAME + "=?",
                new String[]{packageName}, null, null, null, null);
        AppAttribute appAttribute = null;
        if (cursor != null && cursor.moveToFirst()) {
             appAttribute = new AppAttribute(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3));
            Log.e(LOG, "getAppFromPackage: " + appAttribute.getIdApp() + " " + appAttribute.getPackageName() +
                    " " + appAttribute.getIsInstall() + " " + appAttribute.getIsShowing());
        }
        return appAttribute;
    }

    public ArrayList<AppAttribute> getAllApp() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_LIST_APP;
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<AppAttribute> appAttributes = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                AppAttribute appAttribute = new AppAttribute();
                appAttribute.setIdApp(Integer.parseInt(cursor.getString(0)));
                appAttribute.setPackageName(cursor.getString(1));
                appAttribute.setIsInstall(cursor.getString(2));
                appAttribute.setIsShowing(cursor.getString(3));
                appAttributes.add(appAttribute);
                Log.e("ABC", appAttribute.getIdApp() + appAttribute.getPackageName() + appAttribute.getIsInstall() + appAttribute.getIsShowing());
            } while (cursor.moveToNext());
        } else {
            Log.e("AAA", "Data null");
        }

        return appAttributes;
    }

    //    get count of TABLE_LIST_APP
    public long getCount() {
        database = getReadableDatabase();
        return DatabaseUtils.queryNumEntries(database, TABLE_LIST_APP);
    }
}
