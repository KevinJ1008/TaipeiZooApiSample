package com.kevinj1008.taipeizooapisample;

import com.kevinj1008.taipeizooapisample.base.BasePresenter;
import com.kevinj1008.taipeizooapisample.base.BaseView;

public interface TaipeiZooContract {

    interface View extends BaseView<Presenter> {

        void showMainUi();
    }

    interface Presenter extends BasePresenter {

        void transToMain();
    }

}
