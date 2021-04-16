package com.example.librarymanagementsystem.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.librarymanagementsystem.DatabaseHandler;
import com.example.librarymanagementsystem.DbModel;
import com.example.librarymanagementsystem.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegisration extends AppCompatActivity {
    Toolbar tb;
    CardView register;
    TextInputEditText id1,name1,pass1,mail1,mno1;
    TextInputLayout id,name,pass,mail,mno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_regisration);
        tb=findViewById(R.id.toolBar);
        register=findViewById(R.id.returnBookCard);
        id1=findViewById(R.id.userID1);
        name1=findViewById(R.id.userName1);
        pass1=findViewById(R.id.userPass1);
        mail1=findViewById(R.id.userMail1);
        mno1=findViewById(R.id.userPhone1);

        id=findViewById(R.id.userID);
        name=findViewById(R.id.userName);
        pass=findViewById(R.id.userPass);
        mail=findViewById(R.id.userMail);
        mno=findViewById(R.id.userPhone);

        register.setOnClickListener(new UserRegister());

        tb.setNavigationIcon(R.drawable.arrow_back);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(UserRegisration.this);
            }
        });
    }
    class UserRegister implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if (id1.getText().toString().isEmpty() || id1.getText().toString().contains(" "))
                id.setError("Invalid user id");
            else id.setError(null);
            if (mail1.getText().toString().isEmpty() || !mail1.getText().toString().contains("@") || !mail1.getText().toString().contains("."))
                mail.setError("Invalid mail id");
            else mail.setError(null);
            if (name1.getText().toString().isEmpty()) name.setError("Invalid user name");
            else name.setError(null);
            if (pass1.getText().toString().isEmpty() || pass1.getText().toString().length()<8 &&!isValidPassword(pass1.getText().toString())) pass.setError("Invalid password");
            else pass.setError(null);
            if (mno1.getText().toString().isEmpty() || mno1.getText().toString().length() < 10)
                mno.setError("Invalid mobile number");
            else mno.setError(null);
            if (id.getError() == null && mail.getError() == null && name.getError() == null && pass.getError() == null && mno.getError() == null) {
                DatabaseHandler db = new DatabaseHandler(UserRegisration.this);
                DbModel model = new DbModel();
                model.setStud_id(id1.getText().toString());
                model.setStud_name(name1.getText().toString() + " id = " + id1.getText());
                model.setStud_pass(pass1.getText().toString());
                model.setStud_ph(mno1.getText().toString());
                model.setStud_mail(mail1.getText().toString());
                model.setBook(0);
                db.addStud(model);
                Toast.makeText(UserRegisration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                id1.setText("");
                mail1.setText("");
                name1.setText("");
                pass1.setText("");
                mno1.setText("");
            }
        }
    }
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}