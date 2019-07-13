package com.kevinj1008.taipeizooapisample.mvvm.repository

import com.kevinj1008.taipeizooapisample.api.ApiConstants.ZOO_RID
import com.kevinj1008.taipeizooapisample.api.RetrofitManager
import com.kevinj1008.taipeizooapisample.model.Zoo
import com.kevinj1008.taipeizooapisample.model.response.ZooResponse
import com.kevinj1008.taipeizooapisample.mvvm.api.ZooService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

object MainRepository {

    private const val SCOPE = "scope"
    private const val RID = "rid"

    private val zooService = RetrofitManager.createService(ZooService::class.java)

    fun loadZoo(): Single<List<Zoo>> {
        val parameterMap = mutableMapOf<String, String>()
        parameterMap[SCOPE] = "resourceAquire"
        parameterMap[RID] = ZOO_RID
        return zooService.loadZoo(parameterMap)
                .subscribeOn(Schedulers.io())
                .doOnSuccess { it.isSuccessful }
                .map { zooListGenerator(it.body()!!) }
    }

    private fun zooListGenerator(response: ZooResponse): List<Zoo> {
        val result = response.zooResult
        return result.zoos
    }
}