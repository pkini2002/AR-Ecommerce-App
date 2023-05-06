package com.example.ar_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class DetailActivity extends AppCompatActivity {
    TextView detailDesc,detailTitle,detailCost,detailCategory;
    ImageView detailImage;
    Button cart;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        detailCost = findViewById(R.id.detailLang);
        detailCategory=findViewById(R.id.detailCategory);
        cart=findViewById(R.id.cart);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            detailCost.setText(bundle.getString("Cost"));
            detailCategory.setText(bundle.getString("Category"));
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("cart");

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataTitle = detailTitle.getText().toString();
                String dataCost = detailCost.getText().toString();
                String dataDescription = detailDesc.getText().toString();
                String dataCategory=detailCategory.getText().toString();
                String dataImage = bundle.getString("Image");

                // Create a unique key for the item in the cart
                String key = databaseReference.push().getKey();

                // Create a new cart item object
//                CartItem cartItem = new CartItem(dataTitle, dataDescription, dataImage, dataCost);
                DataClass dataItem = new DataClass(dataTitle, dataDescription, dataCost,dataCategory,dataImage);
                String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                // Add the cart item to the database reference
                databaseReference.child(currentDate).setValue(dataItem);

                Toast.makeText(DetailActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

}