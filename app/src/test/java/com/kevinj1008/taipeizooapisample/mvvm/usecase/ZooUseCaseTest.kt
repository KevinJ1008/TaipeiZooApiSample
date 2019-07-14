package com.kevinj1008.taipeizooapisample.mvvm.usecase

import com.kevinj1008.taipeizooapisample.BaseTest
import com.kevinj1008.taipeizooapisample.model.Zoo
import com.kevinj1008.taipeizooapisample.mvvm.repository.MainRepository
import io.mockk.every
import io.mockk.mockkObject
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test

import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy

class ZooUseCaseTest: BaseTest() {

    @Test
    fun loadZoo() {
        mockkObject(MainRepository)
        mockkObject(ZooUseCase)

        val zooList = spy(arrayListOf<Zoo>()::class.java)
        val testObserver = TestObserver<List<Zoo>>()

        every { MainRepository.loadZoo() } returns Single.just(zooList)

        ZooUseCase.loadZoo().subscribe(testObserver)

        testObserver.assertValue(zooList)
    }

    @Test
    fun loadZooFlowable() {
        mockkObject(MainRepository)
        mockkObject(ZooUseCase)

        val zooList = spy(arrayListOf<Zoo>()::class.java)
        val zooA = Zoo()
        zooA.id = 123
        val zooB = Zoo()
        zooB.id = 456
        val zooC = Zoo()
        zooC.id = 789
        zooList.add(zooA)
        zooList.add(zooB)
        zooList.add(zooC)

        val testSubscriber = TestSubscriber<Zoo>()

        every { MainRepository.loadZoo() } returns Single.just(zooList)

        ZooUseCase.loadZooFlowable().subscribe(testSubscriber)

        testSubscriber.awaitTerminalEvent()
        testSubscriber.awaitCount(3)
        testSubscriber.assertValueAt(0, zooA)
        testSubscriber.assertValueAt(1, zooB)
        testSubscriber.assertValueAt(2, zooC)
    }
}