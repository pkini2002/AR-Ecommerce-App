package com.example.ar_ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class shareActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageSlider imageSlider;

    private DrawerLayout drawerLayout;
    Toolbar toolbar;
    String nameFromDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_share);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.nav_home:
                Intent i=new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.nav_about:
                i=new Intent(this, aboutActivity.class);
                startActivity(i);
                break;
            case R.id.nav_settings:
                i=new Intent(this,settingsActivity.class);
                startActivity(i);
                break;
            case R.id.nav_share:
                i=new Intent(this,shareActivity.class);
                startActivity(i);
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void passUserData(){

        Intent intent = getIntent();
        nameFromDB = intent.getStringExtra("name");

    }
    @Override
    public void onBackPressed() {
        passUserData();
        Intent intent = new Intent(shareActivity.this, MainActivity.class);
        intent.putExtra("name", nameFromDB);
        startActivity(intent);
    }
}
