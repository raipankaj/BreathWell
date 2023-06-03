package com.alltechies.breathwell.data

import com.alltechies.timer.data.Action

data class Breath(
    val type: String,
    val url: String,
    val action: List<Action>
)