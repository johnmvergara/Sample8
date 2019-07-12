package com.example.sample8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Activity2 extends AppCompatActivity {

    TextView txtName, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        txtName = findViewById(R.id.tvName);
        txtPassword = findViewById(R.id.tvPassword);
    }

    public void displayUser(View v) {
        SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        txtName.setText(sp.getString("user", ""));
        txtPassword.setText(sp.getString("pwd", ""));
    }

    public void goPrevious(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    /* Para madisplay yung nakasave sa internal na makikita sa user.txt using toast */
    public void displayInternal(View v) {
        FileInputStream fis = null;
        StringBuffer buffer = new StringBuffer();
        int letter = 0;
        try {
            fis = openFileInput("user.txt");
            while ((letter = fis.read()) != -1) {
                buffer.append((char) letter);
            }
            Toast.makeText(this, buffer.toString(), Toast.LENGTH_LONG).show();
            /* firstLetter = "" + (char)fis.read();*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        txtName.setText(buffer.substring(0, buffer.indexOf(",")));
        txtPassword.setText(buffer.substring(buffer.indexOf(",")+1));
    }

    /* Para madisplay yung nakasave sa external na makikita sa sdcard na user2.text using toast */
    public void displayExternal(View v) {
        FileInputStream fis = null;
        StringBuffer buffer = new StringBuffer();
        int letter = 0;
        try {
            File file = new File(getExternalFilesDir(null), "user2.text"); //matic na siya na hahanap ng pat
            fis = new FileInputStream(file);
            while ((letter = fis.read()) != -1) {
                buffer.append((char) letter);
            }
            Toast.makeText(this, buffer.toString(), Toast.LENGTH_LONG).show();
            /* firstLetter = "" + (char)fis.read();*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        txtName.setText(buffer.substring(0, buffer.indexOf(",")));
        txtPassword.setText(buffer.substring(buffer.indexOf(",")+1));
    }
}
