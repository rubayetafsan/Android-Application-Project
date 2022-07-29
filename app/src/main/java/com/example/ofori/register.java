package com.example.ofori;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ofori.MainActivity;

public class register extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        try{
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );
        DatabaseHelper dbHelper;
        dbHelper = new DatabaseHelper( this, "User",null,1);
        Button addData = (Button) findViewById( R.id.btn_register);
        EditText username=(EditText)findViewById(R.id.et_1);
        EditText password=(EditText)findViewById(R.id.et_2);
        EditText rpassword=(EditText)findViewById(R.id.et_3);
        addData.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    if(!(password.getText().toString().equals(rpassword.getText().toString())))
                    {
                        Toast.makeText(register.this,"please repeat the same password",Toast.LENGTH_LONG).show();
                        return;
                    }
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    Cursor cursor = db.query("User",null,null,null,null,null,"name DESC");
                    boolean flag=true;
                    while(cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        if(name.equals(username.getText().toString())) {
                            flag=false;
                            Toast.makeText(register.this,"the username has been registered",Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    if(flag) {
                        dbHelper.add(username.getText().toString(), password.getText().toString());
                        Toast.makeText(register.this,"register success!!!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(register.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                catch (Exception e)
                {
                    System.out.println("错误如下:"+e);
                }
            }
        });}
        catch(Exception e)
        {
            System.out.println("error"+e);
        }
    }
}
