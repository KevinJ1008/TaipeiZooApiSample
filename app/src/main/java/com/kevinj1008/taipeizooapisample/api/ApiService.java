package com.kevinj1008.taipeizooapisample.api;

import com.kevinj1008.taipeizooapisample.model.Plant;
import com.kevinj1008.taipeizooapisample.model.Zoo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

//    @FormUrlEncoded
    @GET(ApiConstants.API_URL + ApiConstants.ZOO_RID)
    Call<Zoo> getZoos();

    @GET(ApiConstants.API_URL + ApiConstants.PLANT_RID + "result/results")
    Call<ArrayList<Plant>> getPlants();
}
