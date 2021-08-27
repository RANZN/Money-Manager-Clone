package com.ranzan.moneymanagerclone.fragments;

import android.graphics.Color;
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

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class StatsFragment extends Fragment {
    private PieChart pieChart;
    private TextView card, account, cash, perCard, perAccount, perCash;
    private static ArrayList<Data> dataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false);
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
        cash.setText(totalCash + "");
        card.setText(totalCard + "");
        account.setText(totalAccount + "");
        perCard.setText(String.format("%.2f", (totalCard / (totalAccount + totalCash + totalCard)) * 100));
        perAccount.setText(String.format("%.2f", (totalAccount / (totalAccount + totalCash + totalCard)) * 100));
        perCash.setText(String.format("%.2f", (totalCash / (totalAccount + totalCash + totalCard)) * 100));

        pieChart.addPieSlice(
                new PieModel(
                        "Cash",
                        (float) totalCash,
                        Color.parseColor("#474747")));
        pieChart.addPieSlice(
                new PieModel(
                        "Card",
                        (float) totalCard,
                        Color.parseColor("#3F51B5")));
        pieChart.addPieSlice(
                new PieModel(
                        "Account",
                        (float) totalAccount,
                        Color.parseColor("#E13A3A")));
        pieChart.startAnimation();
    }


    private void initViews(View view) {
        pieChart = view.findViewById(R.id.pieChart);
        card = view.findViewById(R.id.tvCard);
        account = view.findViewById(R.id.tvAccount);
        cash = view.findViewById(R.id.itemCash);
        perAccount = view.findViewById(R.id.perAccount);
        perCard = view.findViewById(R.id.perCard);
        perCash = view.findViewById(R.id.perCash);
    }
}