package com.kevinj1008.taipeizooapisample.api;

import com.kevinj1008.taipeizooapisample.model.response.PlantResponse;
import com.kevinj1008.taipeizooapisample.model.response.ZooResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET(ApiConstants.API_URL + ApiConstants.ZOO_RID)
    Single<ZooResponse> getZooResponse();

    @GET(ApiConstants.API_URL + ApiConstants.PLANT_RID)
    Single<PlantResponse> getPlantResponse();
}
