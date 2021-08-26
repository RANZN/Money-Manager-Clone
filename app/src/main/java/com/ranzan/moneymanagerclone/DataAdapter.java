package com.ranzan.moneymanagerclone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataViewHolder> {
    private ItemClickListener itemClickListener;
    private ArrayList<Data> dataList = new ArrayList<>();

    public DataAdapter(ArrayList<Data> data, ItemClickListener itemClickListener) {
        this.dataList = data;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_item, parent, false);
        return new DataViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Data data = dataList.get(position);
        holder.setData(data);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
