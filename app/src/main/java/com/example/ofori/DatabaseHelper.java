package com.example.ofori;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_User = "create table User (name text primary key,password text);";
    private Context mContext;
    private SQLiteDatabase db;
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
        try {
            db = getReadableDatabase();
        }
        catch (Exception e)
        {
            System.out.println("错误如下:"+e);
        }
    }
    public void add(String name,String password ){
        db.execSQL("INSERT INTO User(name,password)VALUES(?,?)",new Object[]{name,password});
    }
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText(mContext, "Create succeeded.", Toast.LENGTH_LONG).show();
//调用SQLiteDatabase的execSQL()方法执行建表语句
        db.execSQL(CREATE_User);
//弹出一个Toast提示创建成
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
