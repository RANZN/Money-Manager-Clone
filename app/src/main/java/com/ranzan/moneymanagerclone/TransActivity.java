package com.ranzan.moneymanagerclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ranzan.moneymanagerclone.fragments.AccountsFragment;
import com.ranzan.moneymanagerclone.fragments.SettingFragment;
import com.ranzan.moneymanagerclone.fragments.StatsFragment;
import com.ranzan.moneymanagerclone.fragments.TransFragment;

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
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getExtras() != null && intent.getExtras().containsKey("data")) {
                ArrayList<Data> temp = (ArrayList<Data>) intent.getExtras().get("data");
                dataList.addAll(temp);
            }
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

            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                AccountsFragment accountsFragment = new AccountsFragment();
                fragmentTransaction.add(R.id.fragmentContainer, accountsFragment).addToBackStack("Account").commit();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                SettingFragment settingFragment = new SettingFragment();
                fragmentTransaction.add(R.id.fragmentContainer, settingFragment).addToBackStack("Setting").commit();
            }
        });
    }

}