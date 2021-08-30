package com.ranzan.moneymanagerclone.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.ranzan.moneymanagerclone.Login.LoginActivity;
import com.ranzan.moneymanagerclone.Login.SignUpActivity;
import com.ranzan.moneymanagerclone.Login.UserData;
import com.ranzan.moneymanagerclone.PreferenceHelper;
import com.ranzan.moneymanagerclone.R;
import com.ranzan.moneymanagerclone.TransActivity;

public class SettingFragment extends Fragment {
    private Button login, signUp, signOut;
    private TextView name;
    private static String userName;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("user");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intiViews(view);
        userName = PreferenceHelper.getStringFromPreference(getContext(), "userName");
        if (userName != null) {
            name.setText(userName.toUpperCase());
        }
    }

    private void intiViews(View view) {
        name = view.findViewById(R.id.name);
        login = view.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        signUp = view.findViewById(R.id.signUP);
        signOut = view.findViewById(R.id.signOUT);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), TransActivity.class));
                PreferenceHelper.writeStringToPreference(getContext(), "userName", "");
                name.setText("");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        ifLogin();
        if (currentUser != null)
            login();
    }

    private void login() {
        if (firebaseAuth.getCurrentUser() != null) {
            GenericTypeIndicator<UserData> genericTypeIndicator = new GenericTypeIndicator<UserData>() {
            };
            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    UserData userData = snapshot.getValue(genericTypeIndicator);
                    name.setText(userData.getName().toUpperCase());
                    PreferenceHelper.writeStringToPreference(getContext(), "userName", userData.getName());
                    ifLogin();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void ifLogin() {
        if (!name.getText().toString().equals("")) {
            signOut.setVisibility(View.VISIBLE);
            signUp.setVisibility(View.GONE);
            login.setVisibility(View.GONE);
        } else {
            signUp.setVisibility(View.VISIBLE);
            signOut.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);
        }
    }
}