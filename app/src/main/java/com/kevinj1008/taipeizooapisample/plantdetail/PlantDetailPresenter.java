package com.kevinj1008.taipeizooapisample.plantdetail;

import com.kevinj1008.taipeizooapisample.model.Plant;

import static androidx.core.util.Preconditions.checkNotNull;

public class PlantDetailPresenter implements PlantDetailContract.Presenter {

    private PlantDetailContract.View mPlantDetailView;
    private Plant mPlant;

    public PlantDetailPresenter(PlantDetailContract.View plantDetailView, Plant plant) {
        mPlantDetailView = checkNotNull(plantDetailView, "plantDetailView cannot be null!");
        this.mPlant = plant;
        mPlantDetailView.setPresenter(this);
    }

    @Override
    public void start() {
        showPlants(mPlant);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void showPlants(Plant plant) {
        mPlantDetailView.showPlantDetailUi(plant);
    }

    @Override
    public void refresh() {

    }


}
