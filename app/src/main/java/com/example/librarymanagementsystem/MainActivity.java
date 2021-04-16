package com.example.librarymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.librarymanagementsystem.admin.AdminLogin;
import com.example.librarymanagementsystem.user.UserLogin;

public class MainActivity extends AppCompatActivity {
    CardView admin,user;
    Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        admin=findViewById(R.id.admin);
        user=findViewById(R.id.user);
        tb=findViewById(R.id.toolBar);
//        tb.setTitle("Library Management System");


        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AdminLogin.class));
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserLogin.class));
            }
        });
    }
}