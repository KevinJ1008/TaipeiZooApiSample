package com.kevinj1008.taipeizooapisample.provider

import io.reactivex.Scheduler

interface AppScheduler {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler

    fun single(): Scheduler

    fun trampoline(): Scheduler
}