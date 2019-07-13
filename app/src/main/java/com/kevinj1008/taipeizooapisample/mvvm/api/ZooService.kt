package com.kevinj1008.taipeizooapisample.mvvm.api

import com.kevinj1008.taipeizooapisample.api.ApiConstants.BASE_URL
import com.kevinj1008.taipeizooapisample.model.response.ZooResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ZooService {

    companion object {
        private const val DATALIST = "datalist/"
        private const val API_ACCESS = "apiAccess"
    }

    @GET(BASE_URL + DATALIST + API_ACCESS)
    fun loadZoo(@QueryMap keyMap: Map<String, String>): Single<Response<ZooResponse>>
}