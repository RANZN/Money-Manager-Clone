package com.ranzan.moneymanagerclone.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ranzan.moneymanagerclone.R;
import com.ranzan.moneymanagerclone.TransActivity;

public class SignUpActivity extends AppCompatActivity {
    private EditText name, email, password;
    private Button btnSignUP;
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("user");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        intiViews();
        signUP();
    }

    private void signUP() {
        btnSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSignUP.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isComplete()) {
                                    UserData userData = new UserData(name.getText().toString(), email.getText().toString());
                                    databaseReference.child(firebaseAuth.getCurrentUser().getUid())
                                            .setValue(userData)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Intent intent = new Intent(SignUpActivity.this, TransActivity.class);
                                                    startActivity(intent);
                                                    Toast.makeText(getBaseContext(), "SignUp successful", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            }
                        });
            }
        });
    }

    private void intiViews() {
        name = findViewById(R.id.signName);
        email = findViewById(R.id.signEmail);
        password = findViewById(R.id.signPassword);
        btnSignUP = findViewById(R.id.btnSignUp);
        progressBar = findViewById(R.id.progressBar);
    }
}