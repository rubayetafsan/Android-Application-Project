package com.example.ofori;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private Button mBtnLogin,mBtnRegister;
    private EditText username,password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] user = {null};
        final String[] pwd = {null};
        username=(EditText)findViewById(R.id.et_1);
        password=(EditText)findViewById(R.id.et_2);
        mBtnLogin=(Button)findViewById(R.id.btn_login);
        mBtnRegister=(Button)findViewById(R.id.btn_register);
        DatabaseHelper dbHelper = new DatabaseHelper( this, "User",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Cursor cursor = db.query("User",null,null,null,null,null,"name DESC");
                                             boolean flag=true;
                                             while(cursor.moveToNext()){
                                                 String name = cursor.getString(cursor.getColumnIndex("name"));
                                                 String  pwd= cursor.getString(cursor.getColumnIndex("password"));
                                                 if(name.equals(username.getText().toString())&&pwd.equals(password.getText().toString())) {
                                                     flag=false;
                                                     Toast.makeText(MainActivity.this,"login success!!!",Toast.LENGTH_LONG).show();
                                                     Intent intent = new Intent(MainActivity.this, Success.class);
                                                     startActivity(intent);
                                                 }
                                             }
                                             if(flag)
                                                 Toast.makeText(MainActivity.this,"user or password is wrong",Toast.LENGTH_LONG).show();
                                         }
                                     }
        );
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,register.class);
                startActivity(intent);
            }
        });
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                user[0] =s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pwd[0]=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
