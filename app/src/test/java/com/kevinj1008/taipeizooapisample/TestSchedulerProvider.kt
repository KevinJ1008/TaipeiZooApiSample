package com.kevinj1008.taipeizooapisample

import androidx.annotation.VisibleForTesting
import com.kevinj1008.taipeizooapisample.provider.AppScheduler
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Implementation of the [AppScheduler] making all [Scheduler]s execute
 * synchronously so we can easily run assertions in our tests.
 *
 *
 * To achieve this, we are using the [io.reactivex.internal.schedulers.TrampolineScheduler] from the [Schedulers] class.
 */
@VisibleForTesting
object TestSchedulerProvider : AppScheduler {
    override fun single(): Scheduler = Schedulers.trampoline()

    override fun trampoline(): Scheduler = Schedulers.trampoline()

    override fun computation(): Scheduler = Schedulers.trampoline()

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun ui(): Scheduler = Schedulers.trampoline()
}