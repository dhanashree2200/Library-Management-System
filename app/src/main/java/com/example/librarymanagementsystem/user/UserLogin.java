package com.example.librarymanagementsystem.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.librarymanagementsystem.DatabaseHandler;
import com.example.librarymanagementsystem.DbModel;
import com.example.librarymanagementsystem.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class UserLogin extends AppCompatActivity {
    TextView register;
    CardView userLogin;
    TextInputEditText userID,userPass;
    TextInputLayout idEL,passEL;
    SharedPreferences sharedpreferences;
    Toolbar tb;

    public static final String MyPREFERENCES = "UserLogin" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        register=findViewById(R.id.register);
        userLogin=findViewById(R.id.adminLoginSuccess);
        userID=findViewById(R.id.uID1);
        userPass=findViewById(R.id.uPass1);
        idEL=findViewById(R.id.uID);
        passEL=findViewById(R.id.uPass);
        tb=findViewById(R.id.toolBar);

        tb.setNavigationIcon(R.drawable.arrow_back);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(UserLogin.this);
            }
        });

        sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String name=sharedpreferences.getString("Name",null);
        if(name!=null){
            startActivity(new Intent(UserLogin.this, UserMainPage.class));
        }

        userLogin.setOnClickListener(new Userlogin());
        register.setOnClickListener(new UserRegistration());
    }
    class UserRegistration implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            startActivity(new Intent(UserLogin.this, UserRegisration.class));
        }
    }

    class Userlogin implements View.OnClickListener{
        @Override
        public void onClick(View v) {
                int f=0;
                DatabaseHandler db=new DatabaseHandler(UserLogin.this);
                List<DbModel> list=db.getAllStud();
                for (DbModel stud:list){
                    if(stud.getStud_id().equals(userID.getText().toString()) && stud.getStud_pass().equals(userPass.getText().toString())){
                        f=1;
                        Log.d("TAG", " ");
                        Toast.makeText(UserLogin.this, "Success", Toast.LENGTH_LONG).show();
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("Name", userID.getText().toString());
                        editor.putString("Password", userPass.getText().toString());
                        editor.apply();
                    }
                }
                if(f==0) Toast.makeText(UserLogin.this, "No such user found", Toast.LENGTH_LONG).show();
                if(f==1) startActivity(new Intent(UserLogin.this,UserMainPage.class));


        }
    }
}