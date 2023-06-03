package com.alltechies.breathwell.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alltechies.breathwell.data.Breath
import com.alltechies.breathwell.repo.BreathRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * A ViewModel that sends data fetched from the repo to the composable
 * through state flow object.
 */
class BreathViewModel: ViewModel() {

    private val _exerciseTypes = MutableStateFlow<List<Breath>>(emptyList())
    val exerciseTypes = _exerciseTypes.asStateFlow()

    private val _breathExercise = MutableStateFlow<Breath?>(null)
    val breathExercise = _breathExercise.asStateFlow()


    fun getListOfAllExercises() {
        _exerciseTypes.value = BreathRepo.getAllBreathingExercises()
    }

    fun getExerciseBasedOnType(type: String?) {
        viewModelScope.launch(Dispatchers.Default) {
            BreathRepo.getBreathingExerciseBasedOnType(type)
                .collect { breath ->
                    _breathExercise.value = breath
                }
        }
    }
}