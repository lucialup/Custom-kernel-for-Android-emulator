package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            FileOutputStream fout = openFileOutput("testFile.txt", MODE_PRIVATE);
            String testText = "Hello, world!";
            fout.write(testText.getBytes());
            fout.close();

            FileInputStream fin = openFileInput("testFile.txt");
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = fin.read()) != -1) {
                sb.append((char) ch);
            }
            fin.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}