package com.cilegondev.dmovie.core.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private val RESOURCE: String? = "GLOBAL"
    private val idlingResource = CountingIdlingResource(RESOURCE)
    fun increment() {
        idlingResource.increment()
    }
    fun decrement() {
        idlingResource.decrement()
    }
    fun getEspressoIdlingResource(): IdlingResource {
        return idlingResource
    }
}