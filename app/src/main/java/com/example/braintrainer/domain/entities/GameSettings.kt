package com.example.braintrainer.domain.entities

import java.io.Serializable

data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameDuration: Int
) : Serializable {
}