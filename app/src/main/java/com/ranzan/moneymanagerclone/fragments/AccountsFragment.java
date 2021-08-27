package com.ranzan.moneymanagerclone.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ranzan.moneymanagerclone.Data;
import com.ranzan.moneymanagerclone.R;

import java.util.ArrayList;

public class AccountsFragment extends Fragment {
    private static ArrayList<Data> dataList = new ArrayList<>();
    private TextView cash, account, card, asset, liability, total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accounts, container, false);
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
        setData();
    }

    private void setData() {
        double totalCash = 0;
        double totalCard = 0;
        double totalAccount = 0;
        for (Data item : dataList) {
            if (item.getAccount().equals("Cash")) {
                totalCash += item.getAmount();
            } else if (item.getAccount().equals("Card")) {
                totalCard += item.getAmount();
            } else if (item.getAccount().equals("Account")) {
                totalAccount += item.getAmount();
            }
        }
    }

    private void initViews(View view) {
        cash = view.findViewById(R.id.cash);
        account = view.findViewById(R.id.account);
        card = view.findViewById(R.id.card);
        asset = view.findViewById(R.id.assetsValue);
        liability = view.findViewById(R.id.liaValue);
        total = view.findViewById(R.id.totalValue);
    }

}