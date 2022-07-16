package com.example.braintrainer.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val isWon: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
)  : Parcelable {
    val countOfRightAnswersStr: String
        get() = countOfRightAnswers.toString()
    val countOfQuestionsStr: String
        get() = countOfQuestions.toString()
}