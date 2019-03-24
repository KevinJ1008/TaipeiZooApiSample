package com.kevinj1008.taipeizooapisample.api.bean;

import com.kevinj1008.taipeizooapisample.model.Plant;

import java.util.ArrayList;

public class GetPlants {

    private ArrayList<Plant> mPlants;

    public GetPlants() {
        mPlants = new ArrayList<>();
    }

    public ArrayList<Plant> getPlants() {
        return mPlants;
    }

    public void setPlants(ArrayList<Plant> plants) {
        mPlants = plants;
    }

}
