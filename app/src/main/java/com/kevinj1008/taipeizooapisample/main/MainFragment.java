package com.kevinj1008.taipeizooapisample.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.kevinj1008.taipeizooapisample.R;
import com.kevinj1008.taipeizooapisample.TaipeiZoo;
import com.kevinj1008.taipeizooapisample.TaipeiZooActivity;
import com.kevinj1008.taipeizooapisample.adapter.MainAdapter;
import com.kevinj1008.taipeizooapisample.api.bean.GetZoos;
import com.kevinj1008.taipeizooapisample.model.Zoo;
import com.kevinj1008.taipeizooapisample.mvvm.viewmodel.MainViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.core.util.Preconditions.checkNotNull;

public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter mPresenter;
    private MainAdapter mMainAdapter;
    private ProgressBar mProgressBar;

    private MainViewModel mainViewModel;

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
//        mMainAdapter = new MainAdapter();
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        //load zoos normally
//        mainViewModel.getZooList().observe(this, zoos -> mMainAdapter.setZoos(zoos));

        //load zoo one by one
        mainViewModel.getZoo().observe(this, zoo -> mMainAdapter.addZooToList(zoo));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_main);
        mProgressBar = root.findViewById(R.id.main_progressbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(TaipeiZoo.getAppContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(TaipeiZoo.getAppContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mMainAdapter);

        mainViewModel.getLoadProgress().observe(this, this::showProgressBar);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mPresenter.start();

        //load zoos normally
//        mainViewModel.loadZoo();

        //load zoo one by one
        mainViewModel.loadZooFlow();
    }

    @Override
    public void onResume() {
        super.onResume();
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
        ((TaipeiZooActivity) getActivity()).transToZooDetail(zoo);
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void refreshUi() {

    }

}
