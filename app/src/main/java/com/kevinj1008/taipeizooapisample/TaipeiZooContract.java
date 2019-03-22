package com.kevinj1008.taipeizooapisample;

import com.kevinj1008.taipeizooapisample.base.BasePresenter;
import com.kevinj1008.taipeizooapisample.base.BaseView;
import com.kevinj1008.taipeizooapisample.model.Plant;
import com.kevinj1008.taipeizooapisample.model.Zoo;

public interface TaipeiZooContract {

    interface View extends BaseView<Presenter> {

        void showMainUi();

        void showZooDetailUi(Zoo zoo);

        void showPlantDetailUi(Plant plant);
    }

    interface Presenter extends BasePresenter {

        void transToMain();

        void transToZooDetail(Zoo zoo);

        void transToPlantDetail(Plant plant);

    }

}
