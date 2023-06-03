package com.alltechies.breathwell.datasource

import androidx.compose.runtime.snapshotFlow
import com.alltechies.breathwell.data.Breath
import com.alltechies.timer.data.Action
import kotlinx.coroutines.flow.flow

object FakeDataSource {

    val breathList = listOf(
        Breath(
            "Deep Breathing",
            "https://picsum.photos/200/300.webp",
            listOf(
                Action(4000, "Inhale"),
                Action(4000, "Exhale"),
                Action(4000, "Inhale"),
                Action(4000, "Exhale"),
                Action(4000, "Inhale"),
                Action(4000, "Exhale"),
                Action(4000, "Inhale"),
                Action(4000, "Exhale"),
            )
        ),
        Breath(
            "Box Breathing",
            "https://picsum.photos/200/300.jpg",
            listOf(
                Action(4000, "Inhale"),
                Action(4000, "Hold"),
                Action(4000, "Exhale"),
                Action(4000, "Hold"),
                Action(4000, "Inhale"),
                Action(4000, "Hold"),
                Action(4000, "Exhale"),
                Action(4000, "Hold"),
                Action(4000, "Inhale"),
                Action(4000, "Hold"),
                Action(4000, "Exhale"),
                Action(4000, "Hold"),
                Action(4000, "Inhale"),
                Action(4000, "Hold"),
                Action(4000, "Exhale"),
                Action(4000, "Hold"),
            )
        ),
        Breath(
            "4-7-8 Breathing",
            "https://picsum.photos/200/400.webp",
            listOf(
                Action(4000, "Inhale"),
                Action(7000, "Hold"),
                Action(8000, "Exhale"),
                Action(4000, "Inhale"),
                Action(7000, "Hold"),
                Action(8000, "Exhale"),
                Action(4000, "Inhale"),
                Action(7000, "Hold"),
                Action(8000, "Exhale"),
                Action(4000, "Inhale"),
                Action(7000, "Hold"),
                Action(8000, "Exhale"),
            )
        ),
        Breath(
            "Alternate Nostril",
            "https://picsum.photos/200/600.webp",
            listOf(
                Action(4000, "Inhale"),
                Action(4000, "Exhale"),
                Action(4000, "Inhale"),
                Action(4000, "Exhale"),
                Action(4000, "Inhale"),
                Action(4000, "Exhale"),
            )
        ),
        Breath(
            "Relaxation Breath",
            "https://picsum.photos/300/600.jpg",
            listOf(
                Action(4000, "Inhale"),
                Action(6000, "Exhale"),
                Action(4000, "Inhale"),
                Action(6000, "Exhale"),
                Action(4000, "Inhale"),
                Action(6000, "Exhale"),
            )
        )
    )

    fun getBasedOnType(type: String?) = flow {
        emit(breathList.firstOrNull { it.type == type })
    }
}