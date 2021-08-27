package com.ranzan.moneymanagerclone;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class AddNewDataActivity extends AppCompatActivity {
    private TextView date;
    private TextView account;
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
    private ArrayList<Data> dataList = new ArrayList<>();
    private int typeValue;
    private String dateNTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_data);
        initViews();
        selectedBtn();
        pickDateAndTime();
        otherData();
        addData();
    }

    private void otherData() {
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddNewDataActivity.this);
                builder.setTitle("Account");
                String[] accountList = {"Cash", "Account", "Card"};
                builder.setItems(accountList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                account.setText("Cash");
                                break;
                            case 1:
                                account.setText("Account");
                                break;
                            case 2:
                                account.setText("Card");
                                break;

                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }


    private void pickDateAndTime() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int hh = Calendar.getInstance().get(Calendar.HOUR);
        int min = Calendar.getInstance().get(Calendar.MINUTE);
        date.setText(String.format("%02d", dayOfMonth) + "/" + String.format("%02d", (month + 1)) + "/" + year + "   " + String.format("%02d", hh) + ":" + String.format("%02d", min));
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateNTime = "";
                getTimeDialog(hh, min);
                getDateDialog();
            }

        });

    }

    private void getTimeDialog(int hh, int min) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                dateNTime += String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute);
                date.setText(dateNTime);
            }
        }, hh, min, true);
        timePickerDialog.show();
    }

    private void getDateDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateNTime = String.format("%02d", dayOfMonth) + "/" + String.format("%02d", (month + 1)) + "/" + year + "   ";
            }
        },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddNewDataActivity.this, TransActivity.class);
        startActivity(intent);
    }

    private void addData() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (account.getText().toString().length() == 0) {
                    Toast.makeText(AddNewDataActivity.this, "Please select specific assect", Toast.LENGTH_SHORT).show();
                } else if (amount.length() > 0) {
                    dataList.add(new Data(typeValue, date.getText().toString(),
                            date.getText().toString(),
                            account.getText().toString(),
                            category.getText().toString(),
                            Integer.parseInt(amount.getText().toString()),
                            note.getText().toString(),
                            description.getText().toString()));
                    Intent intent = new Intent(AddNewDataActivity.this, TransActivity.class);
                    intent.putExtra("data", dataList);
                    startActivity(intent);
                } else amount.setError("Enter amount");
            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (account.getText().toString().length() == 0) {
                    Toast.makeText(AddNewDataActivity.this, "Please select specific assect", Toast.LENGTH_SHORT).show();
                } else if (amount.length() > 0) {
                    dataList.add(new Data(typeValue, date.getText().toString(),
                            date.getText().toString(),
                            account.getText().toString(),
                            category.getText().toString(),
                            Integer.parseInt(amount.getText().toString()),
                            note.getText().toString(),
                            description.getText().toString()));
                    Intent intent = new Intent(AddNewDataActivity.this, AddNewDataActivity.class);
                    intent.putExtra("data", dataList);
                    startActivity(intent);
                } else amount.setError("Enter amount");
            }
        });
    }

    private void selectedBtn() {
        radioGroup.check(R.id.rgExpenses);
        expenses.setTextColor(getResources().getColor(R.color.expense));
        expenses.setBackground(getResources().getDrawable(R.drawable.bg2));
        btnSave.setBackgroundColor(getResources().getColor(R.color.expense));
        typeValue = 2;
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
                        typeValue = 1;
                        break;
                    case R.id.rgExpenses:
                        expenses.setTextColor(getResources().getColor(R.color.expense));
                        expenses.setBackground(getResources().getDrawable(R.drawable.bg2));
                        btnSave.setBackgroundColor(getResources().getColor(R.color.expense));
                        income.setTextColor(getResources().getColor(R.color.black));
                        income.setBackground(getResources().getDrawable(R.drawable.bg));
                        transfer.setTextColor(getResources().getColor(R.color.black));
                        transfer.setBackground(getResources().getDrawable(R.drawable.bg));
                        typeValue = 2;
                        break;
                    case R.id.rgTransfer:
                        transfer.setTextColor(getResources().getColor(R.color.transfer));
                        transfer.setBackground(getResources().getDrawable(R.drawable.bg2));
                        btnSave.setBackgroundColor(getResources().getColor(R.color.transfer));
                        expenses.setTextColor(getResources().getColor(R.color.black));
                        expenses.setBackground(getResources().getDrawable(R.drawable.bg));
                        income.setTextColor(getResources().getColor(R.color.black));
                        income.setBackground(getResources().getDrawable(R.drawable.bg));
                        typeValue = 3;
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
                finish();
            }
        });
        expenses = findViewById(R.id.rgExpenses);
        income = findViewById(R.id.rgIncome);
        transfer = findViewById(R.id.rgTransfer);
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getExtras() != null && intent.getExtras().containsKey("data")) {
                dataList = (ArrayList<Data>) intent.getExtras().get("data");
            }
        }
    }

}