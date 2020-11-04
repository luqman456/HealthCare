package com.example.healthcare.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.healthcare.Model.BPLevel;
import com.example.healthcare.Model.Sugar;
import com.example.healthcare.Model.User;
import com.example.healthcare.Model.UserData;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";
    // User table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_SUGAR = "sugar";
    private static final String TABLE_BP = "bp";

    private static final String COLUMN_WEEK_ONE = "week_one";
    private static final String COLUMN_WEEK_TWO = "week_two";
    private static final String COLUMN_WEEK_THREE = "week_three";
    private static final String COLUMN_WEEK_FOUR = "week_four";
    private static final String COLUMN_WEEK_Five = "week_five";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_AGE = "user_age";
    private static final String COLUMN_USER_HEIGHT = "user_height";
    private static final String COLUMN_USER_WEIGHT = "user_weight";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    private static final String COLUMN_SUGAR_ID = "sugar_id";
    private static final String COLUMN_USER_SUGAR_LEVEL = "user_sugar_level";
    private static final String COLUMN_USER_SUGAR_LEVEL_DATE = "user_sugar_level_date";



    private static final String COLUMN_BP_ID = "pb_id";
    private static final String COLUMN_USER_BP_SYSTOLIC_PRESSURE = "user_bp_systolic_pressure_level";
    private static final String COLUMN_USER_BP_DIASTOLIC_PRESSURE = "user_bp_diastolic_pressure_level";
    private static final String COLUMN_USER_BP_LEVEL_DATE = "user_bp_level_date";

    private static final String COLUMN_WEEKS = "weeks";
    private static final String COLUMN_MONTH_YEAR = "month_year";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_AGE + " INTEGER," + COLUMN_USER_HEIGHT + " TEXT,"
            + COLUMN_USER_WEIGHT + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";


    private String CREATE_SUGAR_TABLE = "CREATE TABLE " + TABLE_SUGAR + "("
            + COLUMN_SUGAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_SUGAR_LEVEL + " INTEGER,"
            + COLUMN_USER_SUGAR_LEVEL_DATE + " TEXT,"
            + COLUMN_WEEKS + " TEXT," + COLUMN_MONTH_YEAR + " INTEGER,"
            + COLUMN_USER_EMAIL + " TEXT" + ")";

    private String CREATE_BP_TABLE = "CREATE TABLE " + TABLE_BP + "("
            + COLUMN_BP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_BP_SYSTOLIC_PRESSURE + " INTEGER,"
            + COLUMN_USER_BP_DIASTOLIC_PRESSURE + " INTEGER,"+ COLUMN_WEEKS + " TEXT,"+ COLUMN_MONTH_YEAR + " INTEGER,"
            + COLUMN_USER_BP_LEVEL_DATE+ " TEXT,"
            +COLUMN_USER_EMAIL+ " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_SUGAR_TABLE = "DROP TABLE IF EXISTS " + TABLE_SUGAR;
    private String DROP_BP_TABLE = "DROP TABLE IF EXISTS " + TABLE_BP;

    private List<Integer> list = new ArrayList<>();


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_SUGAR_TABLE);
        db.execSQL(CREATE_BP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_SUGAR_TABLE);
        db.execSQL(DROP_BP_TABLE);
        // Create tables again
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_AGE, user.getAge());
        values.put(COLUMN_USER_HEIGHT, user.getHeight());
        values.put(COLUMN_USER_WEIGHT, user.getWeight());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public User getAllUser(String email) {

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_EMAIL + " = " + email;
        Cursor cursor = db.rawQuery("select * from " + TABLE_USER+" where user_email =?", new String[]{email});
        User user = new User();
        if (cursor.moveToFirst()) {
            do {

                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setAge(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_AGE))));
                user.setHeight(cursor.getString(cursor.getColumnIndex(COLUMN_USER_HEIGHT)));
                user.setWeight(cursor.getString(cursor.getColumnIndex(COLUMN_USER_WEIGHT)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return user;
    }

    public User getData(String email, String password) {
        User user = new User();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from " + TABLE_USER + " where " + COLUMN_USER_EMAIL + "=?" + " and " + COLUMN_USER_PASSWORD + "=?", new String[] {email, password});
        if(cursor.moveToFirst()){
            user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
        }
        return user;
    }

    public boolean updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_AGE,user.getAge());
        values.put(COLUMN_USER_HEIGHT,user.getHeight());
        values.put(COLUMN_USER_WEIGHT,user.getWeight());

       int update = db.update(TABLE_USER, values, COLUMN_USER_EMAIL + " = ?", new String[]{String.valueOf(user.getEmail())});
       db.close();
       if(update >0){
            return true;
        }
        return false;
    }

    public boolean checkUser(String email) {

        String[] columns = {COLUMN_USER_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password) {
        String[] columns = {COLUMN_USER_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public Long addUserSugar(Sugar sugar,String email,String weeks,int month,int year) {
        int month_year = Integer.parseInt(month+""+year);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_SUGAR_LEVEL, sugar.getSugar_level());
        values.put(COLUMN_USER_SUGAR_LEVEL_DATE, sugar.getDate());
        values.put(COLUMN_WEEKS,weeks);
        values.put(COLUMN_MONTH_YEAR,month_year);
        values.put(COLUMN_USER_EMAIL, email);
        // Inserting Row
        long rowInserted = db.insert(TABLE_SUGAR, null, values);
        db.close();
        return rowInserted;
    }

    public List<Integer> getSugar(String email,String weeks,int month,int year) {
        try {
            int month_year = Integer.parseInt(month + "" + year);
            SQLiteDatabase db = this.getReadableDatabase();

            //String sql = "SELECT * FROM " + TABLE_USER + " WHERE "  +COLUMN_USER_SUGAR_LEVEL_DATE+ "BETWEEN" +start_date+ "AND" +end_date+ "" + COLUMN_USER_EMAIL + " = " + email;

            //String dateQuery = "SELECT user_sugar_level FROM sugar WHERE user_sugar_level_date >= ? AND user_sugar_level_date < ?";

            String dateQuery = "SELECT user_sugar_level FROM sugar WHERE weeks = ? AND month_year = ? AND user_email = ?";
            String[] selectionArgs = {weeks, String.valueOf(month_year), email};
            Cursor cursor = db.rawQuery(dateQuery, selectionArgs);
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getCount() > 0) {
                        list.add(Integer.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_SUGAR_LEVEL))));
                    }
                } while (cursor.moveToNext());
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
     return list;
    }

    public ArrayList<Sugar> getSugarTableData(int month,int year){
        ArrayList<Sugar> sugars = new ArrayList<>();
        try {
            int month_year = Integer.parseInt(month + "" + year);
            SQLiteDatabase db = this.getReadableDatabase();

            String dateQuery = "SELECT * FROM sugar WHERE month_year = ? AND user_email = ?";
            String[] selectionArgs = {String.valueOf(month_year), UserData.user_email};
            Cursor cursor = db.rawQuery(dateQuery, selectionArgs);
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getCount() > 0) {
                        Sugar sugar = new Sugar(Integer.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_SUGAR_ID))),
                                Integer.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_SUGAR_LEVEL))), cursor.getString(cursor.getColumnIndex(COLUMN_USER_SUGAR_LEVEL_DATE)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_MONTH_YEAR)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_WEEKS)));
                        sugars.add(sugar);
                    }
                } while (cursor.moveToNext());
            }
            return sugars;
        }catch (Exception e){
            e.printStackTrace();
        }
        return sugars;
    }


    public boolean checkUserSugar(String email, String date) {
        String[] columns = {COLUMN_SUGAR_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_SUGAR_LEVEL_DATE + " = ?";
        String[] selectionArgs = {email, date};
        Cursor cursor = db.query(TABLE_SUGAR,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUserBP(String email, String date) {
        String[] columns = {COLUMN_BP_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_BP_LEVEL_DATE + " = ?";
        String[] selectionArgs = {email, date};
        Cursor cursor = db.query(TABLE_BP,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public Long addUserBP(BPLevel bpLevel,String email,String weeks,int month,int year) {
        int month_year = Integer.parseInt(month+""+year);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_BP_SYSTOLIC_PRESSURE, bpLevel.getSystolic());
        values.put(COLUMN_USER_BP_DIASTOLIC_PRESSURE, bpLevel.getDiastolic());
        values.put(COLUMN_USER_BP_LEVEL_DATE, bpLevel.getDate());
        values.put(COLUMN_WEEKS,weeks);
        values.put(COLUMN_MONTH_YEAR,month_year);
        values.put(COLUMN_USER_EMAIL, email);
        // Inserting Row
        long rowInserted = db.insert(TABLE_BP, null, values);
        db.close();
        return rowInserted;
    }

    public ArrayList<BPLevel> getBPTableData(int month,int year){
        ArrayList<BPLevel> bpLevels = new ArrayList<>();
        try {
            int month_year = Integer.parseInt(month + "" + year);
            SQLiteDatabase db = this.getReadableDatabase();

            String dateQuery = "SELECT * FROM bp WHERE month_year = ? AND user_email = ?";
            String[] selectionArgs = {String.valueOf(month_year), UserData.user_email};
            Cursor cursor = db.rawQuery(dateQuery, selectionArgs);
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getCount() > 0) {
                        BPLevel bpLevel = new BPLevel(Integer.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_BP_ID))),
                                Integer.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_BP_SYSTOLIC_PRESSURE))), cursor.getInt(cursor.getColumnIndex(COLUMN_USER_BP_DIASTOLIC_PRESSURE)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_MONTH_YEAR)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_WEEKS)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_USER_BP_LEVEL_DATE)));
                        bpLevels.add(bpLevel);
                    }
                } while (cursor.moveToNext());
            }
            return bpLevels;
        }catch (Exception e){
            e.printStackTrace();
        }
        return bpLevels;
    }
}
