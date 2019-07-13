package com.kevinj1008.taipeizooapisample.mvvm.usecase

import com.kevinj1008.taipeizooapisample.model.Zoo
import com.kevinj1008.taipeizooapisample.mvvm.repository.MainRepository
import io.reactivex.Flowable
import io.reactivex.rxkotlin.zipWith
import java.util.concurrent.TimeUnit

object ZooUseCase {

    private const val ZOO_LIST_DELAY = 3_000L

    fun loadZooFlowable(): Flowable<Zoo> {
        return MainRepository.loadZoo()
                .flatMapPublisher { Flowable.fromIterable(it) }
                .zipWith(Flowable.interval(ZOO_LIST_DELAY, TimeUnit.MILLISECONDS)) {zoo, _ -> zoo}
                .onBackpressureLatest()

    }

}