package com.example.ar_ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText loginEmail,loginPassword;
    private TextView signupRedirectText;
    private Button loginButton;

    TextView forgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth=FirebaseAuth.getInstance();
        loginEmail=findViewById(R.id.login_email);
        loginPassword=findViewById(R.id.login_password);
        loginButton=findViewById(R.id.login_button);
        signupRedirectText=findViewById(R.id.signupRedirectText);
        forgotPassword=findViewById(R.id.forgot_password);
        loginButton.setOnClickListener(new View.OnClickListener(){
             @Override
            public void onClick(View view){
                 String email=loginEmail.getText().toString();
                 String pass=loginPassword.getText().toString();

                 if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                      if(!pass.isEmpty()){
                          auth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                              @Override
                              public void onSuccess(AuthResult authResult) {
                                  Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                  startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                  finish();
                              }
                          }).addOnFailureListener(new OnFailureListener() {
                              @Override
                              public void onFailure(@NonNull Exception e) {
                                  Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                              }
                          });
                      }else{
                          loginPassword.setError("Password cannot be empty");
                      }
                 }else if(email.isEmpty()){
                      loginEmail.setError("Email cannot be empty");
                 }else{
                     loginEmail.setError("Please enter valid email");
                 }
             }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                View dialogView=getLayoutInflater().inflate(R.layout.dialog_forgot,null);
                EditText emailBox=dialogView.findViewById(R.id.emailBox);

                builder.setView(dialogView);
                AlertDialog dialog= builder.create();

                dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userEmail=emailBox.getText().toString();
                        if(TextUtils.isEmpty(userEmail)&&!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                            Toast.makeText(LoginActivity.this, "Enter your registered email", Toast.LENGTH_SHORT).show();
                             return;
                        }
                        auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Check your email", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }else {
                                    Toast.makeText(LoginActivity.this, "Unable to send failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialogView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                if(dialog.getWindow()!=null){
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                dialog.show();
            }
        });
    }
}