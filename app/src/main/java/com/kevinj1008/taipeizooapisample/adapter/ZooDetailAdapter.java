package com.kevinj1008.taipeizooapisample.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevinj1008.taipeizooapisample.R;
import com.kevinj1008.taipeizooapisample.api.bean.GetPlants;
import com.kevinj1008.taipeizooapisample.model.Plant;
import com.kevinj1008.taipeizooapisample.model.Zoo;
import com.kevinj1008.taipeizooapisample.util.Constants;
import com.kevinj1008.taipeizooapisample.zoodetail.ZooDetailContract;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ZooDetailAdapter extends RecyclerView.Adapter {

    private ZooDetailContract.Presenter mPresenter;
    private ArrayList<Plant> mPlants;
    private Zoo mZoo;

    public ZooDetailAdapter(GetPlants plants, ZooDetailContract.Presenter presenter) {
        this.mPlants = plants.getPlants();
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Constants.VIEWTYPE_ZOO_DETAIL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zoo_detail, parent, false);
            return new ZooDetailItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zoo_detail_plant, parent, false);
            return new ZooDetailPlantItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ZooDetailItemViewHolder) {
            Picasso.get()
                    .load(mZoo.getPicture())
                    .into(((ZooDetailItemViewHolder) holder).mZooPicture);
            ((ZooDetailItemViewHolder) holder).mZooInfo.setText(mZoo.getInfo());
            if ("".equals(mZoo.getMemo())) {
                ((ZooDetailItemViewHolder) holder).mZooMemo.setText(R.string.zoo_no_memo);
            } else {
                ((ZooDetailItemViewHolder) holder).mZooMemo.setText(mZoo.getMemo());
            }
            ((ZooDetailItemViewHolder) holder).mZooName.setText(mZoo.getName());
            if (mPlants.isEmpty()) {
                ((ZooDetailItemViewHolder) holder).mPlantInfo.setVisibility(View.VISIBLE);
            } else {
                ((ZooDetailItemViewHolder) holder).mPlantInfo.setVisibility(View.GONE);
            }
        } else {
            if (position == 1) {
                ((ZooDetailPlantItemViewHolder) holder).mSeparator.setVisibility(View.GONE);
            } else {
                ((ZooDetailPlantItemViewHolder) holder).mSeparator.setVisibility(View.VISIBLE);
            }
            if (!"".equals(mPlants.get(position - 1).getPicture01())) {
                Picasso.get()
                        .load(mPlants.get(position - 1).getPicture01())
                        .placeholder(R.drawable.all_picture_placeholder)
                        .into(((ZooDetailPlantItemViewHolder) holder).mPlantImage);
            }
            if (!"".equals(mPlants.get(position - 1).getNameCh())) {
                ((ZooDetailPlantItemViewHolder) holder).mPlantNameCh.setText(mPlants.get(position - 1).getNameCh());
            } else {
                ((ZooDetailPlantItemViewHolder) holder).mPlantNameCh.setText(R.string.no_plant_detail_name_ch);
            }
            if (!"".equals(mPlants.get(position - 1).getAlsoKnown())) {
                ((ZooDetailPlantItemViewHolder) holder).mPlantAlsoKnow.setText(mPlants.get(position - 1).getAlsoKnown());
            } else {
                ((ZooDetailPlantItemViewHolder) holder).mPlantAlsoKnow.setText(R.string.no_plant_detail_also_known);
            }
        }

    }

    @Override
    public int getItemCount() {
        return (mPlants.isEmpty()) ? 1 : mPlants.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return Constants.VIEWTYPE_ZOO_DETAIL;
        } else {
            return Constants.VIEWTYPE_ZOO_PLANT;
        }
    }

    private class ZooDetailItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView mZooPicture;
        private TextView mZooInfo;
        private TextView mZooMemo;
        private TextView mZooName;
        private TextView mWebButton;
        private TextView mPlantInfo;

        public ZooDetailItemViewHolder(@NonNull View itemView) {
            super(itemView);

            mZooPicture = itemView.findViewById(R.id.zoo_detail_image);
            mZooInfo = itemView.findViewById(R.id.zoo_detail_info);
            mZooMemo = itemView.findViewById(R.id.zoo_detail_memo);
            mZooName = itemView.findViewById(R.id.zoo_detail_name);
            mWebButton = itemView.findViewById(R.id.web_button);
            mPlantInfo = itemView.findViewById(R.id.no_plant_info);

            mWebButton.setOnClickListener(openWeb);
        }

        private View.OnClickListener openWeb = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.web_button) {
                    String url = mZoo.getURL();
                    mPresenter.openZooWeb(url);
                }
            }
        };
    }

    private class ZooDetailPlantItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView mPlantImage;
        private TextView mPlantNameCh;
        private TextView mPlantAlsoKnow;
        private View mSeparator;
        private ConstraintLayout mPlantContainer;

        public ZooDetailPlantItemViewHolder(@NonNull View itemView) {
            super(itemView);

            mPlantImage = itemView.findViewById(R.id.plant_image);
            mPlantNameCh = itemView.findViewById(R.id.plant_name_ch);
            mPlantAlsoKnow = itemView.findViewById(R.id.plant_also_know);
            mSeparator = itemView.findViewById(R.id.plant_separator);
            mPlantContainer = itemView.findViewById(R.id.plant_container);

            mPlantContainer.setOnClickListener(clickListener);
        }

        private View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.plant_container) {
                   mPresenter.openPlantDetail(mPlants.get(getAdapterPosition() - 1));
                }
            }
        };
    }

    public void updateData(GetPlants plants) {
        for (Plant plant : plants.getPlants()) {
            mPlants.add(plant);
        }

        notifyDataSetChanged();
        Log.d(Constants.TAG, "ZooBody Detail Adapter update data size: " + plants.getPlants().size());
    }

    public void initData() {
        mPlants.clear();
        notifyDataSetChanged();
    }

    public void showZoo(Zoo zoo) {
        this.mZoo = zoo;
    }

}
