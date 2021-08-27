package com.ranzan.moneymanagerclone;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class DataViewHolder extends RecyclerView.ViewHolder {
    private TextView category;
    private TextView note;
    private TextView account;
    private TextView amount;
    private TextView date_n_time;
    private ItemClickListener itemClickListener;

    public DataViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
        initViews(itemView);
    }

    private void initViews(View view) {
        category = view.findViewById(R.id.itemAccount);
        note = view.findViewById(R.id.itemNote);
        account = view.findViewById(R.id.itemCategory);
        amount = view.findViewById(R.id.itemAmount);
        date_n_time = view.findViewById(R.id.dateNtime);
    }

    public void setData(Data data) {
        category.setText(data.getCategory());
        note.setText(data.getNote());
        account.setText(data.getAccount());
        amount.setText(data.getAmount() + "");
        date_n_time.setText(data.getDate());
        if (data.getType() == 1) {
            amount.setTextColor(Color.rgb(63, 81, 181));
        } else if (data.getType() == 2) {
            amount.setTextColor(Color.rgb(225, 51, 51));
        } else {
            amount.setTextColor(Color.rgb(71, 71, 71));
        }
    }
}
