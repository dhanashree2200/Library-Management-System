package com.example.librarymanagementsystem.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.librarymanagementsystem.MainActivity;
import com.example.librarymanagementsystem.R;
import com.google.android.material.navigation.NavigationView;

public class AdminMainPage extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_page);
        drawerLayout=findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolBar);

        setSupportActionBar(toolbar);

//        DatabaseHandler db=new DatabaseHandler(this);
//        DbModel dbModel=new DbModel();
//        dbModel.setBook_id("21");
//        dbModel.setBook_title("ABC");
//        dbModel.setBook_auth("Dhanashree");
//        dbModel.setBook_pub("XYZ");
//        dbModel.setStatus(1);
//        db.addBook(dbModel);
//        DbModel dbModel2=new DbModel();
//        dbModel2.setBook_id("213");
//        dbModel2.setBook_title("ABCc");
//        dbModel2.setBook_auth("Dhanashreec");
//        dbModel2.setBook_pub("XYZc");
//        dbModel2.setStatus(1);
//        db.addBook(dbModel2);
//        Log.d("TAG", "onCreate:34 "+db.getIssueCount());
//        List<DbModel> list= null;
//            list = db.getAllIssueBook();
//        Log.d("TAG", "onCreate: 32"+list.toString());
//        for (DbModel allContact:list){
//            Log.d("TAG", "ID: "+allContact.getI_stud_id());
//            Log.d("TAG", "Name "+allContact.getI_book_id());
//            Log.d("TAG", "Phone Number: "+allContact.getI_book_id());
//        }
//        Log.d("TAG", "onCreate: "+db.getStudCount());
//        List<DbModel> list=db.getAllBooks();
//        for (DbModel allContact:list){
//            Log.d("TAG", "ID: "+allContact.getBook_id());
//            Log.d("TAG", "Status: "+allContact.getStatus());
//        }
//        List<DbModel> list1=db.getAllStud();
//        for (DbModel allContact:list1){
//            Log.d("TAG", "SID: "+allContact.getStud_id());
//            Log.d("TAG", "SStatus "+allContact.getBook());
//        }
//        DbModel dbModel1=db.getSingleBook("213");
//        Log.d("TAG", "pub2 = "+dbModel1.getBook_pub());
//        Log.d("TAG", "auth = "+dbModel1.getBook_auth());
//


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.id);
        navUsername.setText("ADMIN");

        Fragment fragment1=null;
        fragment1=new DashboardFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment1).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.close,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                int id=item.getItemId();
                switch (id){
                    case R.id.addBook:
                        fragment= new AddBookFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.updateBook:
                        fragment= new UpdateBookFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.viewBook:
                        fragment= new ViewBookFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.issuedBook:
                        fragment= new IssuedBooksFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.returnBook:
                        fragment= new ReturnBookFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.issueBook:
                        fragment= new IssueBookFragment();
                        loadFragment(fragment);
                        break;

                    default:
                }
                return false;
            }

            private void loadFragment(Fragment fragment) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame,fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                fragmentTransaction.addToBackStack(null);
            }
        });

        navUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame,new DashboardFragment()).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                fragmentTransaction.addToBackStack(null);
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AdminMainPage.this, MainActivity.class));
        super.onBackPressed();
    }
}