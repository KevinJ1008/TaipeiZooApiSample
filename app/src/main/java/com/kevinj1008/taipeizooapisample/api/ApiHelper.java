//package com.kevinj1008.taipeizooapisample.api;
//
//import com.kevinj1008.taipeizooapisample.api.bean.GetZoos;
//import com.kevinj1008.taipeizooapisample.model.Zoo;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//
//public class ApiHelper {
//
//
//    public static GetZoos getZoos() throws IOException {
//
//        Retrofit retrofit = new Retrofit.Builder().build();
//        ApiService service = retrofit.create(ApiService.class);
//        Call<ArrayList<Zoo>> call = service.getZoos();
//        call.enqueue(new Callback<ArrayList<Zoo>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Zoo>> call, Response<ArrayList<Zoo>> response) {
//                ArrayList<Zoo> zoos = response.body();
//
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Zoo>> call, Throwable t) {
//
//            }
//        });
//
//    }
//}
