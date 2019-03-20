package com.kevinj1008.taipeizooapisample.api.task;

import android.os.AsyncTask;
import android.util.Log;

import com.kevinj1008.taipeizooapisample.api.ApiConstants;
import com.kevinj1008.taipeizooapisample.api.ApiService;
import com.kevinj1008.taipeizooapisample.api.bean.GetZoos;
import com.kevinj1008.taipeizooapisample.api.callback.GetZoosCallback;
import com.kevinj1008.taipeizooapisample.model.Zoo;
import com.kevinj1008.taipeizooapisample.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetZoosTask extends AsyncTask<Object, Void, GetZoos> {

    private GetZoosCallback mCallback;
    private String mErrorMessage;
//    private int mPaging;

    public GetZoosTask(GetZoosCallback callback) {
        mCallback = callback;
        mErrorMessage = "";
//        mPaging = paging;
    }

    @Override
    protected GetZoos doInBackground(Object[] objects) {
        final GetZoos bean = new GetZoos();

//        try {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<Zoo> call = service.getZoos();
        call.enqueue(new Callback<Zoo>() {
            @Override
            public void onResponse(Call<Zoo> call, Response<Zoo> response) {
                Log.d(Constants.TAG, "response body: " + response.body().toString());
                Zoo zoo = response.body();
                for (int i = 0; i < bean.getZoos().size(); i++) {
                    bean.getZoos().add(zoo);
                }
            }

            @Override
            public void onFailure(Call<Zoo> call, Throwable t) {
                Log.d(Constants.TAG, "Api fail: " + t.getMessage());
            }
        });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return bean;
    }

    @Override
    protected void onPostExecute(GetZoos bean) {
        super.onPostExecute(bean);

        if (bean != null) {
            mCallback.onCompleted(bean);
        } else if (!mErrorMessage.equals("")) {

            mCallback.onError(mErrorMessage);
        } else {

            Log.d(Constants.TAG, "GetZoos fail");
        }
    }
}
