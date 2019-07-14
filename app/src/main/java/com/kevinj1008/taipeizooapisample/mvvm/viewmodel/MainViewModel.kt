package com.kevinj1008.taipeizooapisample.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import com.kevinj1008.taipeizooapisample.base.BaseViewModel
import com.kevinj1008.taipeizooapisample.model.Zoo
import com.kevinj1008.taipeizooapisample.mvvm.repository.MainRepository
import com.kevinj1008.taipeizooapisample.mvvm.usecase.ZooUseCase
import com.kevinj1008.taipeizooapisample.provider.AppScheduler
import com.kevinj1008.taipeizooapisample.util.SingleLiveData
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.combineLatest
import java.util.concurrent.TimeUnit

class MainViewModel(
//        private val appScheduler: AppScheduler
) : BaseViewModel() {

    var zooList = MutableLiveData<List<Zoo>>()
    var zoo = MutableLiveData<Zoo>()
    val loadProgress = MutableLiveData<Boolean>()
    val loadError = SingleLiveData<Throwable>()

    fun loadZoo() {
        launch {
            ZooUseCase.loadZoo()
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { loadProgress.value = true }
                    .doFinally { loadProgress.value = false }
                    .subscribe({ zooList.value = it },
                            { loadError.value = it })

        }
    }

    fun loadZooFlow() {
        launch {
            ZooUseCase.loadZooFlowable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .combineLatest(Flowable.interval(0, 3_000L, TimeUnit.SECONDS))
                    .onBackpressureLatest()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onZooFlowData(it) },
                            { loadError.value = it })
        }
    }

    private fun onZooFlowData(data: Pair<Zoo, Long>) {
        zoo.value = data.first
    }
}