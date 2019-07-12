package com.example.sample8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText txtName, txtPassword, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = findViewById(R.id.etName);
        txtPassword = findViewById(R.id.etPassword);
    }

    public void goNext(View v){
        Intent i = new Intent(this, Activity2.class);
        startActivity(i);
    }

    public void saveInfo(View v){
        SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("user", txtName.getText().toString());
        editor.putString("pwd", txtPassword.getText().toString());
        editor.commit();
        Toast.makeText(this, "saved in userInfo.xml1", Toast.LENGTH_LONG).show();
    }

    public void saveInternal(View v){
        FileOutputStream fos=null;
        try {
            fos = openFileOutput("user.txt", Context.MODE_PRIVATE);
            fos.write((txtName.getText().toString() + ",").getBytes());
            fos.write(txtPassword.getText().toString().getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "data saved in Internal storage!", Toast.LENGTH_LONG).show();

    }

//sa sd card nakasave to
    public void saveExternal(View v){
        FileOutputStream fos = null;
        File file = new File(getExternalFilesDir(null),"user2.text");
        try {
            fos = new FileOutputStream(file);
            fos.write((txtName.getText().toString() + ",").getBytes());
            fos.write(txtPassword.getText().toString().getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "saved data in external storage...", Toast.LENGTH_LONG).show();
    }
/* //di pa gumana kaya comment muna
    public void savePublic(View v){
        FileOutputStream fos = null;
        File file = new File(getExternalFilesDir() + "/Documents/user4.txt");
        try {
            fos = new FileOutputStream(file);
            fos.write((txtName.getText().toString() + ",").getBytes());
            fos.write(txtPassword.getText().toString().getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "saved data in external storage...", Toast.LENGTH_LONG).show();
    }
    */
//para malocate yung cache, data>data>project name>cache pero di yata to naguupdate
public void saveCache(View v){
    FileOutputStream fos = null;
    File file = new File(getExternalCacheDir(), "user4.txt");
    try {
        fos = new FileOutputStream(file);
        fos.write((txtName.getText().toString() + ",").getBytes());
        fos.write(txtPassword.getText().toString().getBytes());

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    } finally {
        try {
            fos.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    Toast.makeText(this, "saved data in cache storage...", Toast.LENGTH_LONG).show();
    }
}