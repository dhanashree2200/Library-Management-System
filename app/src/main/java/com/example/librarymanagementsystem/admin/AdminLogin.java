package com.example.librarymanagementsystem.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.librarymanagementsystem.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AdminLogin extends AppCompatActivity {
    CardView adminLogin;
    TextInputEditText adminID,adminPass;
    TextInputLayout idEL,passEL;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "AdminLogin" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        adminID=findViewById(R.id.aID1);
        adminPass=findViewById(R.id.uPass1);
        idEL=findViewById(R.id.aID);
        passEL=findViewById(R.id.aPass);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String name=sharedpreferences.getString("Name",null);
        if(name!=null){
            startActivity(new Intent(AdminLogin.this, AdminMainPage.class));
        }

        adminLogin=findViewById(R.id.adminLoginSuccess);
        adminLogin.setOnClickListener(new AdminLoginSuccess());


    }
    class AdminLoginSuccess implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(adminID.getText().toString().equals("admin") && adminPass.getText().toString().equals("admin")){
                Toast.makeText(AdminLogin.this,"Success",Toast.LENGTH_LONG).show();
                passEL.setError(null);
                idEL.setError(null);
                SharedPreferences.Editor editor  = sharedpreferences.edit();
                editor.putString("Name", adminID.getText().toString());
                editor.putString("Password", adminPass.getText().toString());
                editor.apply();
                startActivity(new Intent(AdminLogin.this,AdminMainPage.class));
            }
            if(!adminID.getText().toString().equals("admin")){
                Log.d("TAG", "onClick: ");
                idEL.setError("Invalid User ID");
            }
            else if(adminID.getText().toString().equals("admin")){
                idEL.setError(null);
                idEL.clearFocus();
                passEL.requestFocus();
                adminPass.setCursorVisible(true);
            }
            if(!adminPass.getText().toString().equals("admin")){
                passEL.setError("Invalid Password");
            }
            else if(adminPass.getText().toString().equals("admin")){
                passEL.setError(null);
            }
        }
    }
}