package com.ranzan.moneymanagerclone;

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

public class TransActivity extends AppCompatActivity {
    private FloatingActionButton btn;
    private TextView trans;
    private TextView stats;
    private TextView account;
    private TextView setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);
        initViews();
        addFragment();
    }

    private void initViews() {
        btn = findViewById(R.id.addData);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                AddNewDataFragment addNewDataFragment = new AddNewDataFragment();
                fragmentTransaction.replace(R.id.fragmentContainer, addNewDataFragment).addToBackStack("Add").commit();
                btn.setVisibility(View.GONE);
            }
        });
        trans = findViewById(R.id.trans);
        stats = findViewById(R.id.stats);
        account = findViewById(R.id.accounts);
        setting = findViewById(R.id.settings);

    }

    private void addFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        TransFragment transFragment = new TransFragment();
        fragmentTransaction.add(R.id.fragmentContainer, transFragment);
        fragmentTransaction.commit();
        trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                TransFragment transFragment = new TransFragment();
                fragmentTransaction.replace(R.id.fragmentContainer, transFragment).commit();
            }
        });
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                StatsFragment statsFragment = new StatsFragment();
                fragmentTransaction1.replace(R.id.fragmentContainer, statsFragment).addToBackStack("Stats").commit();
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                AccountsFragment accountsFragment = new AccountsFragment();
                fragmentTransaction.replace(R.id.fragmentContainer, accountsFragment).addToBackStack("Account").commit();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                SettingFragment settingFragment = new SettingFragment();
                fragmentTransaction.replace(R.id.fragmentContainer, settingFragment).addToBackStack("Setting").commit();
            }
        });
    }
}