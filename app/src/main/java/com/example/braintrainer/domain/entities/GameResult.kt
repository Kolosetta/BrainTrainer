package com.example.braintrainer.domain.entities

import java.io.Serializable

data class GameResult(
    val isWon: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
)  : Serializable {
}