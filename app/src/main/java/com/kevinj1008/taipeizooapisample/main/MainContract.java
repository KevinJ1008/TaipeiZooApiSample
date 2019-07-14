package com.kevinj1008.taipeizooapisample.main;

import com.kevinj1008.taipeizooapisample.api.bean.GetZoos;
import com.kevinj1008.taipeizooapisample.base.BasePresenter;
import com.kevinj1008.taipeizooapisample.base.BaseView;
import com.kevinj1008.taipeizooapisample.model.Plant;
import com.kevinj1008.taipeizooapisample.model.Zoo;

import java.util.ArrayList;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void showZoo(GetZoos zoos);

        void showZooDetailUi(Zoo zoo);

        void showProgressBar(boolean show);

        void handleError(Throwable throwable);

        void reloadZoo();

        void refreshUi();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadZoo();

        void showZoo(GetZoos zoo);

        void openZooDetail(Zoo zoo);

        void reload();
    }
}
