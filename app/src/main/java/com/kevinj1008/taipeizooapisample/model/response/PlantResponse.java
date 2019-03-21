package com.kevinj1008.taipeizooapisample.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kevinj1008.taipeizooapisample.model.result.PlantResult;

public class PlantResponse {

    @SerializedName("result")
    @Expose
    private PlantResult mPlantResult;

    public PlantResult getPlantResult() {
        return mPlantResult;
    }

    public void setPlantResult(PlantResult plantResult) {
        this.mPlantResult = plantResult;
    }
}
