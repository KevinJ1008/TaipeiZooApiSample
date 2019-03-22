package com.kevinj1008.taipeizooapisample.zoodetail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevinj1008.taipeizooapisample.R;
import com.kevinj1008.taipeizooapisample.TaipeiZoo;
import com.kevinj1008.taipeizooapisample.TaipeiZooActivity;
import com.kevinj1008.taipeizooapisample.adapter.ZooDetailAdapter;
import com.kevinj1008.taipeizooapisample.api.bean.GetPlants;
import com.kevinj1008.taipeizooapisample.model.Plant;
import com.kevinj1008.taipeizooapisample.model.Zoo;
import com.kevinj1008.taipeizooapisample.util.Constants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.core.util.Preconditions.checkNotNull;

public class ZooDetailFragment extends Fragment implements ZooDetailContract.View {

    private ZooDetailContract.Presenter mPresenter;
    private ZooDetailAdapter mZooDetailAdapter;

    public ZooDetailFragment() {
        // Requires empty public constructor
    }

    public static ZooDetailFragment newInstance() {
        return new ZooDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mZooDetailAdapter = new ZooDetailAdapter(new GetPlants(), mPresenter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_zoo_detail, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_zoodetail);
        recyclerView.setLayoutManager(new LinearLayoutManager(TaipeiZoo.getAppContext()));
//        recyclerView.addItemDecoration(new DividerItemDecoration(TaipeiZoo.getAppContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mZooDetailAdapter);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.start();
    }

    @Override
    public void setPresenter(ZooDetailContract.Presenter presenter) {
//        try {
            mPresenter = checkNotNull(presenter);
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (mPresenter == null) {
//                mPresenter = new ZooDetailPresenter(this);
//            }
//        }
    }

    @Override
    public void showPlants(GetPlants plants) {
        mZooDetailAdapter.updateData(plants);
    }

    @Override
    public void showZooDetailUi(Zoo zoo) {
        mZooDetailAdapter.showZoo(zoo);
    }

    @Override
    public void showPlantDetailUi(Plant plant) {
        ((TaipeiZooActivity) getActivity()).transToPlantDetail(plant);
    }

    @Override
    public void refreshUi() {
        mZooDetailAdapter.initData();
        Log.d(Constants.TAG, "clear Zoo Detail page");
    }

}
