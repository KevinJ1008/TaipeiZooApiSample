//package com.kevinj1008.taipeizooapisample.api;
//
//import android.util.Log;
//
//import com.kevinj1008.taipeizooapisample.api.bean.GetZoos;
//import com.kevinj1008.taipeizooapisample.model.Zoo;
//import com.kevinj1008.taipeizooapisample.model.response.ZooResponse;
//import com.kevinj1008.taipeizooapisample.model.result.ZooResult;
//import com.kevinj1008.taipeizooapisample.util.Constants;
//
//import java.io.IOException;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class ApiHelper {
//
//
//    public static GetZoos getZoos() {
//        final GetZoos zoos = new GetZoos();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ApiConstants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ApiService service = retrofit.create(ApiService.class);
//        Call<ZooResponse> call = service.getZooResponse();
//        call.enqueue(new Callback<ZooResponse>() {
//            @Override
//            public void onResponse(Call<ZooResponse> call, Response<ZooResponse> response) {
//                ZooResponse zooResponse = response.body();
//                Log.d(Constants.TAG, "Get ZooResponse: " + zooResponse.toString());
//
//                ZooResult zooResult = zooResponse.getZooResult();
//                Log.d(Constants.TAG, "Get ZooResult: " + zooResult.toString());
//                zoos.getZoos().addAll(zooResult.getZoos());
//            }
//
//            @Override
//            public void onFailure(Call<ZooResponse> call, Throwable t) {
//                Log.d(Constants.TAG, "Api fail: " + t.getMessage());
//            }
//        });
//
//        return zoos;
//    }
//}
