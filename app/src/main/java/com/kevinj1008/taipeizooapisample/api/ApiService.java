package com.kevinj1008.taipeizooapisample.api;

import com.kevinj1008.taipeizooapisample.model.Plant;
import com.kevinj1008.taipeizooapisample.model.Zoo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface ApiService {

    @FormUrlEncoded
    @GET(ApiConstants.BASE_URL + ApiConstants.API_URL + ApiConstants.ZOO_RID + "result/results")
    Call<List<Zoo>> getZoos();

    @GET(ApiConstants.BASE_URL + ApiConstants.API_URL + ApiConstants.PLANT_RID + "result/results")
    Call<List<Plant>> getPlants();
}
