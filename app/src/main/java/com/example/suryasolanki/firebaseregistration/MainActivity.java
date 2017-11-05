package com.example.suryasolanki.firebaseregistration;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignUp;

    private TextView ViewTextSignIn;

    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();

            startActivity(new Intent(getApplicationContext(),StartActivity.class));
            Log.d("Firebase Check","User Alreasy Exist");
        }

        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText) findViewById(R.id.editTextPassword);

        progressBar=new ProgressBar(this);

        buttonSignUp=(Button)findViewById(R.id.buttonSignup);

        ViewTextSignIn=(TextView)findViewById(R.id.textViewSignin);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               registerUser();
            }
        });



        ViewTextSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private boolean registerUser(){

        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Email is empty",Toast.LENGTH_SHORT).show();

            return false;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Email is empty",Toast.LENGTH_SHORT).show();
            return false;
        }

        try {


            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                finish();
                                startActivity(new Intent(getApplicationContext(), StartActivity.class));
                                Log.d("FirebaseAuth", "onComplete" + task.getException().getMessage());
                            } else {
                                Toast.makeText(getApplicationContext(), "Registration Fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        catch(Exception e){
            e.printStackTrace();
        }

       // Log.d("FirebaseAuth", "onComplete" + task.getException().getMessage());

        return true;
    }


}
