package com.example.ar_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class editProfile extends AppCompatActivity {
    EditText editName,   editContact, editBirthdate;
    TextView editEmail;
    Button saveButton;
    String nameUser, emailUser, contactUser, birthdateUser;
    DatabaseReference reference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        reference = FirebaseDatabase.getInstance().getReference("profile");
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editContact = findViewById(R.id.editContact);
        editBirthdate = findViewById(R.id.editBirthdate);
        saveButton = findViewById(R.id.saveButton);
        showData();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNameChanged() || isContactChanged() || isEmailChanged()||isBirthdateChanged()){
                    Toast.makeText(editProfile.this, "Saved", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(editProfile.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean isNameChanged() {
        if (!nameUser.equals(editName.getText().toString())){
            reference.child(nameUser).child("name").setValue(editName.getText().toString());
            nameUser = editName.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isEmailChanged() {
        if (!emailUser.equals(editEmail.getText().toString())){
            reference.child(nameUser).child("email").setValue(editEmail.getText().toString());
            emailUser = editEmail.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isContactChanged() {
        if (!contactUser.equals(editContact.getText().toString())){
            reference.child(nameUser).child("contact").setValue(editContact.getText().toString());
           contactUser = editContact.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isBirthdateChanged() {
        if (!birthdateUser.equals(editContact.getText().toString())){
            reference.child(nameUser).child("contact").setValue(editContact.getText().toString());
            birthdateUser = editContact.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    public void showData(){
        Intent intent = getIntent();
        nameUser = intent.getStringExtra("name");
        emailUser = intent.getStringExtra("email");
        contactUser = intent.getStringExtra("contact");
        birthdateUser = intent.getStringExtra("birthdate");
        editName.setText(nameUser);
        editEmail.setText(emailUser);
        editContact.setText(contactUser);
        editBirthdate.setText(birthdateUser);
    }
}