package com.kevinj1008.taipeizooapisample;

import com.kevinj1008.taipeizooapisample.main.MainFragment;
import com.kevinj1008.taipeizooapisample.main.MainPresenter;

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
            MAIN
    })

    public @interface FragmentType {}
    public static final String MAIN = "MAIN";

    public static void getType(@FragmentType String type) {
    }

    private MainFragment mMainFragment;
    private MainPresenter mMainPresenter;

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
}
