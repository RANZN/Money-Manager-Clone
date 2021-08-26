package com.ranzan.moneymanagerclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewDataActivity extends AppCompatActivity {
    private EditText date;
    private EditText account;
    private EditText category;
    private EditText amount;
    private EditText note;
    private EditText description;
    private Button btnSave;
    private Button btnContinue;
    private ImageView bookmark;
    private TextView back;
    private RadioGroup radioGroup;
    private RadioButton income;
    private RadioButton expenses;
    private RadioButton transfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_data);
        initViews();
        selectedBtn();
    }

    private void selectedBtn() {
        radioGroup.check(R.id.rgExpenses);
        expenses.setTextColor(getResources().getColor(R.color.expense));
        expenses.setBackground(getResources().getDrawable(R.drawable.bg2));
        btnSave.setBackgroundColor(getResources().getColor(R.color.expense));
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rgIncome:
                        income.setTextColor(getResources().getColor(R.color.income));
                        income.setBackground(getResources().getDrawable(R.drawable.bg2));
                        btnSave.setBackgroundColor(getResources().getColor(R.color.income));
                        expenses.setTextColor(getResources().getColor(R.color.black));
                        expenses.setBackground(getResources().getDrawable(R.drawable.bg));
                        transfer.setTextColor(getResources().getColor(R.color.black));
                        transfer.setBackground(getResources().getDrawable(R.drawable.bg));
                        break;
                    case R.id.rgExpenses:
                        expenses.setTextColor(getResources().getColor(R.color.expense));
                        expenses.setBackground(getResources().getDrawable(R.drawable.bg2));
                        btnSave.setBackgroundColor(getResources().getColor(R.color.expense));
                        income.setTextColor(getResources().getColor(R.color.black));
                        income.setBackground(getResources().getDrawable(R.drawable.bg));
                        transfer.setTextColor(getResources().getColor(R.color.black));
                        transfer.setBackground(getResources().getDrawable(R.drawable.bg));
                        break;
                    case R.id.rgTransfer:
                        transfer.setTextColor(getResources().getColor(R.color.transfer));
                        transfer.setBackground(getResources().getDrawable(R.drawable.bg2));
                        btnSave.setBackgroundColor(getResources().getColor(R.color.transfer));
                        expenses.setTextColor(getResources().getColor(R.color.black));
                        expenses.setBackground(getResources().getDrawable(R.drawable.bg));
                        income.setTextColor(getResources().getColor(R.color.black));
                        income.setBackground(getResources().getDrawable(R.drawable.bg));
                        break;
                }
            }
        });
    }

    private void initViews() {
        date = findViewById(R.id.date);
        account = findViewById(R.id.account);
        category = findViewById(R.id.category);
        amount = findViewById(R.id.amount);
        radioGroup = findViewById(R.id.rg);
        note = findViewById(R.id.note);
        description = findViewById(R.id.description);
        btnSave = findViewById(R.id.btnSave);
        btnContinue = findViewById(R.id.btnContinue);
        bookmark = findViewById(R.id.bookmark);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        expenses = findViewById(R.id.rgExpenses);
        income = findViewById(R.id.rgIncome);
        transfer = findViewById(R.id.rgTransfer);
    }
}