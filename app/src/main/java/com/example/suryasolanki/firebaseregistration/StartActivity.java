package com.example.suryasolanki.firebaseregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {

    private TextView userEmail;
    private Button buttonLogout;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            finish();

            startActivity(new Intent(this,LoginActivity.class));

        }

        FirebaseUser User= firebaseAuth.getCurrentUser();

        userEmail=(TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout=(Button) findViewById(R.id.buttonLogout);

        userEmail.setText("Welcome"+ User.getEmail());

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
            }
        });
    }
}
