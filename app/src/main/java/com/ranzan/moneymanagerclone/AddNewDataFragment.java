package com.ranzan.moneymanagerclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddNewDataFragment extends Fragment {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_data, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
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

    private void initViews(View view) {
        date = view.findViewById(R.id.date);
        account = view.findViewById(R.id.account);
        category = view.findViewById(R.id.category);
        amount = view.findViewById(R.id.amount);
        radioGroup = view.findViewById(R.id.rg);
        note = view.findViewById(R.id.note);
        description = view.findViewById(R.id.description);
        btnSave = view.findViewById(R.id.btnSave);
        btnContinue = view.findViewById(R.id.btnContinue);
        bookmark = view.findViewById(R.id.bookmark);
        back = view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        expenses = view.findViewById(R.id.rgExpenses);
        income = view.findViewById(R.id.rgIncome);
        transfer = view.findViewById(R.id.rgTransfer);
    }
}