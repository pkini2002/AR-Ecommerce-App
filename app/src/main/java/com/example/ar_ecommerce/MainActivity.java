package com.example.ar_ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
//import android.support.v7.widget.Toolbar;
//import android.widget.Toolbar;


import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SwitchCompat switchMode;
    private SessionManager session;
    boolean nightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ImageSlider imageSlider;
    String nameFromDB,emailFromDB,birthdateFromDB,contactFromDB;
    NavigationView navigationView;
    private DrawerLayout drawerLayout;
    Toolbar toolbar;
    CardView shirt,coat,cap,frock,pant,shorts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSlider = findViewById(R.id.ImageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        session = new SessionManager(getApplicationContext());

        slideModels.add(new SlideModel(R.drawable.mobileecommerce, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.onlineshopping, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.coat, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.shirt, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        switchMode = findViewById(R.id.switchMode);
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("nightMode", false);

        if (nightMode) {
            switchMode.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        switchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nightMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("nightMode", false);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("nightMode", true);
                }
                editor.apply();
            }
        });

        // Onclicking the dashboard cards
        shirt=findViewById(R.id.shirtCard);
        coat=findViewById(R.id.coatCard);
        cap=findViewById(R.id.capCard);
        frock=findViewById(R.id.frockCard);
        pant=findViewById(R.id.pantCard);
        shorts=findViewById(R.id.shortCard);

        shirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,settingsActivity.class);
                i.putExtra("category", "T-Shirts");
                startActivity(i);
            }
        });

        coat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,settingsActivity.class);
                i.putExtra("category", "Coats");
                startActivity(i);
            }
        });

        cap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,settingsActivity.class);
                i.putExtra("category", "Caps");
                startActivity(i);
            }
        });

        frock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,settingsActivity.class);
                i.putExtra("category", "Frocks");
                startActivity(i);
            }
        });

        pant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,settingsActivity.class);
                i.putExtra("category", "Pants");
                startActivity(i);
            }
        });

        shorts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,settingsActivity.class);
                i.putExtra("category", "Shorts");
                startActivity(i);
            }
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String userUsername= intent.getStringExtra("name");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("profile");
        Query checkUserDatabase = reference.orderByChild("name").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                    emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                    contactFromDB = snapshot.child(userUsername).child("contact").getValue(String.class);
                    birthdateFromDB = snapshot.child(userUsername).child("birthdate").getValue(String.class);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);


        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.nav_home:

                Intent intent=new Intent(this,MainActivity.class);
                intent.putExtra("name", nameFromDB);
                startActivity(intent);

                break;
            case R.id.nav_about:

                intent=new Intent(this,Myprofile.class);
                intent.putExtra("name", nameFromDB);
                intent.putExtra("email", emailFromDB);
                intent.putExtra("contact", contactFromDB);
                intent.putExtra("birthdate", birthdateFromDB);
                startActivity(intent);

               break;
            case R.id.nav_settings:

                intent=new Intent(this,aboutActivity.class);
                intent.putExtra("name", nameFromDB);
                startActivity(intent);

                break;
                // Implement the add to cart here
            case R.id.nav_share:
                Intent i=new Intent(this,AllProducts.class);
                startActivity(i);
                break;
            case R.id.nav_cart:
                i=new Intent(this,ViewCart.class);
                startActivity(i);

                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();

               session.setLoggedIn(false);
                intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            // Exit the app instead of going back to the login/signup page
            finishAffinity();
        }
    }
}






