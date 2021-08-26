package com.ranzan.moneymanagerclone;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class DataViewHolder extends RecyclerView.ViewHolder {
    private TextView category;
    private TextView note;
    private TextView account;
    private TextView amount;
    private ItemClickListener itemClickListener;

    public DataViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
        initViews(itemView);
    }

    private void initViews(View view) {
        category = view.findViewById(R.id.itemCatogery);
        note = view.findViewById(R.id.itemNote);
        account = view.findViewById(R.id.itemAccount);
        amount = view.findViewById(R.id.itemAmount);
    }

    public void setData(Data data) {
        category.setText(data.getCategory());
        note.setText(data.getNote());
        account.setText(data.getAccount());
        amount.setText(data.getAmount() + "");
    }
}
