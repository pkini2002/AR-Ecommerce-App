package com.example.ar_ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     SwitchCompat switchMode;
     boolean nightMode;
     SharedPreferences sharedPreferences;
     SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageSlider imageSlider=findViewById(R.id.ImageSlider);
        ArrayList<SlideModel>slideModels=new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.mobileecommerce, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.onlineshopping, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.coat, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.shirt, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);

        switchMode=findViewById(R.id.switchMode);
        sharedPreferences=getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode=sharedPreferences.getBoolean("nightMode",false);

        if(nightMode){
            switchMode.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        switchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nightMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor=sharedPreferences.edit();
                    editor.putBoolean("nightMode",false);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor=sharedPreferences.edit();
                    editor.putBoolean("nightMode",true);
                }
                editor.apply();
            }
        });
    }
}