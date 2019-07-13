package com.kevinj1008.taipeizooapisample.mvvm.viewmodel

import com.kevinj1008.taipeizooapisample.BaseTest
import com.kevinj1008.taipeizooapisample.LiveDataTestUtil
import com.kevinj1008.taipeizooapisample.TestSchedulerProvider
import com.kevinj1008.taipeizooapisample.model.Zoo
import com.kevinj1008.taipeizooapisample.model.response.ZooResponse
import com.kevinj1008.taipeizooapisample.model.result.ZooResult
import com.kevinj1008.taipeizooapisample.mvvm.repository.MainRepository
import com.kevinj1008.taipeizooapisample.mvvm.usecase.ZooUseCase
import io.mockk.*
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins



class MainViewModelTest: BaseTest() {
    private val mainViewModel = MainViewModel()

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun loadZoo() {
        mockkObject(MainRepository)
        val zoos = spyk<List<Zoo>>()

        every { MainRepository.loadZoo() } returns Single.just(zoos)

        mainViewModel.loadZoo()

        val result = LiveDataTestUtil.getValue(mainViewModel.zooList)
        Assert.assertEquals(zoos, result)
    }

    @Test
    fun `loadZooFlow on success`() {
        mockkObject(ZooUseCase)
        val zooA = spyk<Zoo>()
        val zooB = mockk<Zoo>()
        val zooC = mockk<Zoo>()

        every { ZooUseCase.loadZooFlowable() } returns Flowable.just(zooA)

        mainViewModel.loadZooFlow()

        val result = LiveDataTestUtil.getValue(mainViewModel.zoo)
        Assert.assertEquals(zooA, result)
    }
}