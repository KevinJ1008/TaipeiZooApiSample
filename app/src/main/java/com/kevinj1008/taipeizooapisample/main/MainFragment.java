package com.kevinj1008.taipeizooapisample.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevinj1008.taipeizooapisample.R;
import com.kevinj1008.taipeizooapisample.TaipeiZoo;
import com.kevinj1008.taipeizooapisample.adapter.MainAdapter;
import com.kevinj1008.taipeizooapisample.api.bean.GetZoos;
import com.kevinj1008.taipeizooapisample.model.Zoo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.core.util.Preconditions.checkNotNull;

public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter mPresenter;
    private MainAdapter mMainAdapter;

    public MainFragment() {
        // Requires empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainAdapter = new MainAdapter(new GetZoos(), mPresenter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(TaipeiZoo.getAppContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(TaipeiZoo.getAppContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mMainAdapter);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.start();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        try {
            mPresenter = checkNotNull(presenter);
        } catch (Exception e) {
            e.printStackTrace();
            if (mPresenter == null) {
                mPresenter = new MainPresenter(this);
            }
        }
    }

    @Override
    public void showZoo(GetZoos zoos) {
        mMainAdapter.updateData(zoos);
    }

    @Override
    public void showZooDetailUi(Zoo zoo) {

    }

    @Override
    public void refreshUi() {

    }

}
