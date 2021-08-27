package com.ranzan.moneymanagerclone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ranzan.moneymanagerclone.Login.UserData;
import com.ranzan.moneymanagerclone.fragments.AccountsFragment;
import com.ranzan.moneymanagerclone.fragments.SettingFragment;
import com.ranzan.moneymanagerclone.fragments.StatsFragment;
import com.ranzan.moneymanagerclone.fragments.TransFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TransActivity extends AppCompatActivity {
    private FloatingActionButton btn;
    private TextView trans;
    private TextView stats;
    private TextView account;
    private TextView setting;
    private static ArrayList<Data> dataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);
        initViews();
        getDataFromIntent();
        addFragment();
        saveData();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dataList);
        editor.putString("dataList", json);
        editor.apply();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        loadData();
        if (intent != null) {
            if (intent.getExtras() != null && intent.getExtras().containsKey("data")) {
                ArrayList<Data> temp = (ArrayList<Data>) intent.getExtras().get("data");
                dataList.addAll(temp);
            }
        }
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("dataList", null);
        Type type = new TypeToken<ArrayList<Data>>() {
        }.getType();

        dataList = gson.fromJson(json, type);
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
    }

    private void initViews() {
        btn = findViewById(R.id.addData);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransActivity.this, AddNewDataActivity.class);
                startActivity(intent);
            }
        });
        trans = findViewById(R.id.trans);
        stats = findViewById(R.id.stats);
        account = findViewById(R.id.accounts);
        setting = findViewById(R.id.settings);

    }

    private void addFragment() {
        trans.setBackground(getResources().getDrawable(R.drawable.nav_bg1));
        trans.setTextColor(getResources().getColor(R.color.black));
        Bundle bundle = new Bundle();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        TransFragment transFragment = new TransFragment();
        bundle.putSerializable("Data", dataList);
        transFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.fragmentContainer, transFragment);
        fragmentTransaction.commit();
        trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                TransFragment transFragment = new TransFragment();
                fragmentTransaction.add(R.id.fragmentContainer, transFragment).commit();
                trans.setBackground(getResources().getDrawable(R.drawable.nav_bg1));
                stats.setBackground(getResources().getDrawable(R.drawable.nav_bg2));
                account.setBackground(getResources().getDrawable(R.drawable.nav_bg2));
                setting.setBackground(getResources().getDrawable(R.drawable.nav_bg2));

            }
        });
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = new Bundle();
                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                StatsFragment statsFragment = new StatsFragment();
                bundle1.putSerializable("Data", dataList);
                statsFragment.setArguments(bundle1);
                fragmentTransaction1.add(R.id.fragmentContainer, statsFragment).addToBackStack("Stats").commit();
                trans.setBackground(getResources().getDrawable(R.drawable.nav_bg2));
                stats.setBackground(getResources().getDrawable(R.drawable.nav_bg1));
                account.setBackground(getResources().getDrawable(R.drawable.nav_bg2));
                setting.setBackground(getResources().getDrawable(R.drawable.nav_bg2));

            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                AccountsFragment accountsFragment = new AccountsFragment();
                fragmentTransaction.add(R.id.fragmentContainer, accountsFragment).addToBackStack("Account").commit();
                trans.setBackground(getResources().getDrawable(R.drawable.nav_bg2));
                stats.setBackground(getResources().getDrawable(R.drawable.nav_bg2));
                account.setBackground(getResources().getDrawable(R.drawable.nav_bg1));
                setting.setBackground(getResources().getDrawable(R.drawable.nav_bg2));
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                SettingFragment settingFragment = new SettingFragment();
                fragmentTransaction.add(R.id.fragmentContainer, settingFragment).addToBackStack("Setting").commit();
                trans.setBackground(getResources().getDrawable(R.drawable.nav_bg2));
                stats.setBackground(getResources().getDrawable(R.drawable.nav_bg2));
                account.setBackground(getResources().getDrawable(R.drawable.nav_bg2));
                setting.setBackground(getResources().getDrawable(R.drawable.nav_bg1));
            }
        });
    }


}