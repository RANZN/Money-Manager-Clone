package com.ranzan.moneymanagerclone.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ranzan.moneymanagerclone.Data;
import com.ranzan.moneymanagerclone.DataAdapter;
import com.ranzan.moneymanagerclone.ItemClickListener;
import com.ranzan.moneymanagerclone.R;

import java.util.ArrayList;
import java.util.Calendar;

public class TransFragment extends Fragment implements ItemClickListener {
    private RecyclerView recyclerView;
    private TextView totalIncome, totalExpenses, totalAmount, date;
    private static ArrayList<Data> dataList = new ArrayList<>();
    private double nTotalIncome = 0, nTotalExpenses = 0, nTotalAmount = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trans, container, false);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            dataList = (ArrayList<Data>) getArguments().getSerializable("Data");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setRecyclerView();
        setTotalAmounts();
        setDateOnTop();
    }

    private void setDateOnTop() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        date.setText(String.format("%02d", day) + "/" + String.format("%02d", month + 1) + "/"+year);
    }

    private void setTotalAmounts() {
        for (Data item : dataList) {
            if (item.getType() == 1) {
                nTotalIncome += item.getAmount();
            } else if (item.getType() == 2) {
                nTotalExpenses += -item.getAmount();
            }
        }
        totalIncome.setText(String.format("%.2f", nTotalIncome));
        totalExpenses.setText(String.format("%.2f", Math.abs(nTotalExpenses)));
        totalAmount.setText(String.format("%.2f", (nTotalIncome + nTotalExpenses)));
    }

    private void setRecyclerView() {
        DataAdapter dataAdapter = new DataAdapter(dataList, TransFragment.this);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(dataAdapter);
        recyclerView.setLayoutManager(layout);
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        totalIncome = view.findViewById(R.id.totalIncome);
        totalExpenses = view.findViewById(R.id.totalExpenses);
        totalAmount = view.findViewById(R.id.totalAmount);
        date = view.findViewById(R.id.givenDate);
    }

    @Override
    public void onItemClicked(Data data, int position) {
        Toast.makeText(getContext(), "HEY", Toast.LENGTH_SHORT).show();
    }
}