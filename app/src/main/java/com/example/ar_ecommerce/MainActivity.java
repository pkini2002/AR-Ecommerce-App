package com.example.ar_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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
    }
}