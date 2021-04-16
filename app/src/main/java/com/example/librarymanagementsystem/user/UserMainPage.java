package com.example.librarymanagementsystem.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.librarymanagementsystem.MainActivity;
import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.admin.DashboardFragment;
import com.google.android.material.navigation.NavigationView;

public class UserMainPage extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);
        drawerLayout=findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        Fragment fragment1=null;
        fragment1=new Welcome();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment1).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.id);
        navUsername.setText("DHANASHREE");

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.close,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                int id=item.getItemId();
                switch (id){
                    case R.id.viewIssueBooks:
                        fragment= new ViewIssueFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.booksAvailable:
                        fragment= new BooksAvailableFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.profile:
                        fragment= new ProfileFragment();
                        loadFragment(fragment);
                        break;
                    default:
            }
            return  false;
        }
            private void loadFragment(Fragment fragment) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame,fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                fragmentTransaction.addToBackStack(null);
            }
            });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UserMainPage.this, MainActivity.class));
        super.onBackPressed();
    }
}