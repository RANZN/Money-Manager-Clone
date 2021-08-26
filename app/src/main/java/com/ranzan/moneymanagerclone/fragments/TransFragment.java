package com.ranzan.moneymanagerclone.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ranzan.moneymanagerclone.R;

public class TransFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView totalIncome, totalExpenses, totalAmount;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trans, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        buildData();
    }

    private void buildData() {

    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        totalIncome = view.findViewById(R.id.totalIncome);
        totalExpenses = view.findViewById(R.id.totalExpenses);
        totalAmount = view.findViewById(R.id.totalAmount);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}