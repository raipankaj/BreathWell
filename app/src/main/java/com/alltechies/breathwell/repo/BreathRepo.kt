package com.alltechies.breathwell.repo

import com.alltechies.breathwell.datasource.FakeDataSource

object BreathRepo {

    fun getAllBreathingExercises() = FakeDataSource.breathList

    fun getBreathingExerciseBasedOnType(type: String?) = FakeDataSource.getBasedOnType(type)

}