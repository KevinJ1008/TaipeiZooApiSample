package com.kevinj1008.taipeizooapisample.repositry;

import com.kevinj1008.taipeizooapisample.api.ApiConstants;
import com.kevinj1008.taipeizooapisample.api.ApiHelper;
import com.kevinj1008.taipeizooapisample.api.ApiService;
import com.kevinj1008.taipeizooapisample.model.response.PlantResponse;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class PlantRepository {

    public static Single<PlantResponse> getPlant() {
        return ApiHelper.get(ApiConstants.BASE_URL)
                .create(ApiService.class)
                .getPlantResponse()
                .subscribeOn(Schedulers.io());
    }
}
