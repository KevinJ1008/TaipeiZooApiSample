package com.kevinj1008.taipeizooapisample;

import com.kevinj1008.taipeizooapisample.main.MainFragment;
import com.kevinj1008.taipeizooapisample.main.MainPresenter;
import com.kevinj1008.taipeizooapisample.model.Plant;
import com.kevinj1008.taipeizooapisample.model.Zoo;
import com.kevinj1008.taipeizooapisample.zoodetail.ZooDetailFragment;
import com.kevinj1008.taipeizooapisample.zoodetail.ZooDetailPresenter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static androidx.core.util.Preconditions.checkNotNull;

public class TaipeiZooPresenter implements TaipeiZooContract.Presenter {

    private TaipeiZooContract.View mTaipeiZooView;
    private FragmentManager mFragmentManager;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            MAIN, ZOO_DETAIL
    })

    public @interface FragmentType {}
    public static final String MAIN = "MAIN";
    public static final String ZOO_DETAIL = "ZOODETAIL";

    public static void getType(@FragmentType String type) {
    }

    private MainFragment mMainFragment;
    private MainPresenter mMainPresenter;

    private ZooDetailFragment mZooDetailFragment;
    private ZooDetailPresenter mZooDetailPresenter;

    public TaipeiZooPresenter(TaipeiZooContract.View taipeiZooView, FragmentManager fragmentManager) {
        mTaipeiZooView = checkNotNull(taipeiZooView, "taipeiZooView cannot be null!");
        mTaipeiZooView.setPresenter(this);

        mFragmentManager = fragmentManager;
    }

    @Override
    public void start() {
        transToMain();
    }

    @Override
    public void transToMain() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mFragmentManager.findFragmentByTag(ZOO_DETAIL) != null) {
            mFragmentManager.popBackStack();
            mZooDetailPresenter.refresh();
        }

        if (mMainFragment == null) mMainFragment = MainFragment.newInstance();

        if (!mMainFragment.isAdded()){
            transaction.add(R.id.main_container, mMainFragment, MAIN);
        } else {
            transaction.show(mMainFragment);
        }

        if (mMainPresenter == null) {
            mMainPresenter = new MainPresenter(mMainFragment);
        }
        transaction.commit();

        mTaipeiZooView.showMainUi();
    }

    @Override
    public void transToZooDetail(Zoo zoo) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mMainFragment != null && mMainFragment.isAdded()) {
            transaction.hide(mMainFragment).addToBackStack(MAIN);
        }

        mZooDetailFragment = ZooDetailFragment.newInstance();
        transaction.add(R.id.main_container, mZooDetailFragment, ZOO_DETAIL).commit();

        mZooDetailPresenter = new ZooDetailPresenter(mZooDetailFragment, zoo);
        mTaipeiZooView.showZooDetailUi(zoo);
    }

    @Override
    public void transToPlantDetail(Plant plant) {

    }
}
