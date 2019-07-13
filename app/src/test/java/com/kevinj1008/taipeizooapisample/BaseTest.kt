package com.kevinj1008.taipeizooapisample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.rules.TestRule

abstract class BaseTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()
}