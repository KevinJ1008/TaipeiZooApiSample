package com.kevinj1008.taipeizooapisample.plantdetail;

import com.kevinj1008.taipeizooapisample.base.BasePresenter;
import com.kevinj1008.taipeizooapisample.base.BaseView;
import com.kevinj1008.taipeizooapisample.model.Plant;

public interface PlantDetailContract {

    interface View extends BaseView<Presenter> {

        void showPlantDetailUi(Plant plant);

        void refreshUi();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void showPlants(Plant plant);

        void refresh();
    }
}
