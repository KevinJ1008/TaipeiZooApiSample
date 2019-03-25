package com.kevinj1008.taipeizooapisample.zoodetail;

import com.kevinj1008.taipeizooapisample.api.bean.GetPlants;
import com.kevinj1008.taipeizooapisample.base.BasePresenter;
import com.kevinj1008.taipeizooapisample.base.BaseView;
import com.kevinj1008.taipeizooapisample.model.Plant;
import com.kevinj1008.taipeizooapisample.model.Zoo;

public interface ZooDetailContract {

    interface View extends BaseView<Presenter> {

        void showPlants(GetPlants plants);

        void showZooDetailUi(Zoo zoo);

        void showPlantDetailUi(Plant plant);

        void showZooWeb(String url);

        void showZooProgressBar(boolean show);

        void refreshUi();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadPlants();

        void showPlants(GetPlants plants);

        void showZoo(Zoo zoo);

        void openPlantDetail(Plant plant);

        void openZooWeb(String url);

        void refresh();
    }
}
