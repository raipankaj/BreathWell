package com.alltechies.breathwell.repo

import com.alltechies.breathwell.datasource.LocalDataSource

object BreathRepo {


    fun getAllBreathingExercises() = LocalDataSource.breathList

    fun getBreathingExerciseBasedOnType(type: String?) = LocalDataSource.getBasedOnType(type)

}