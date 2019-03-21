package com.kevinj1008.taipeizooapisample.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevinj1008.taipeizooapisample.R;
import com.kevinj1008.taipeizooapisample.api.bean.GetZoos;
import com.kevinj1008.taipeizooapisample.main.MainContract;
import com.kevinj1008.taipeizooapisample.model.Zoo;
import com.kevinj1008.taipeizooapisample.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter {

    private MainContract.Presenter mPresenter;
    private ArrayList<Zoo> mZoos;

    public MainAdapter(GetZoos zoos, MainContract.Presenter presenter) {
        mPresenter = presenter;
        this.mZoos = zoos.getZoos();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new MainItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d(Constants.TAG, "MainView Bind");
        Picasso.get()
                .load(mZoos.get(position).getPicture())
                .placeholder(R.drawable.all_picture_placeholder)
                .into(((MainItemViewHolder) holder).mZooPicture);
        ((MainItemViewHolder) holder).mZooName.setText(mZoos.get(position).getName());
        ((MainItemViewHolder) holder).mZooInfo.setText(mZoos.get(position).getInfo());
        if ("".equals(mZoos.get(position).getMemo())) {
            ((MainItemViewHolder) holder).mZooMemo.setText(R.string.zoo_no_memo);
        } else {
            ((MainItemViewHolder) holder).mZooMemo.setText(mZoos.get(position).getMemo());
        }
    }

    @Override
    public int getItemCount() {
        return (mZoos.isEmpty()) ? 0 : mZoos.size();
    }

    private class MainItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mZooPicture;
        private TextView mZooName;
        private TextView mZooInfo;
        private TextView mZooMemo;

        public MainItemViewHolder(@NonNull View itemView) {
            super(itemView);

            mZooPicture = itemView.findViewById(R.id.zoo_picture);
            mZooName = itemView.findViewById(R.id.zoo_name);
            mZooInfo = itemView.findViewById(R.id.zoo_info);
            mZooMemo = itemView.findViewById(R.id.zoo_memo);
        }
    }

    public void updateData(GetZoos zoos) {
        for (Zoo zoo : zoos.getZoos()) {
            mZoos.add(zoo);
        }

        notifyDataSetChanged();
        Log.d(Constants.TAG, "MainAdapter update data");
    }
}
