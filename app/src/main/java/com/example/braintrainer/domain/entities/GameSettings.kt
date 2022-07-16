package com.example.braintrainer.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameDuration: Int
) : Parcelable {
    val minCountOfRightAnswersStr: String
        get() = minCountOfRightAnswers.toString()
    val minPercentOfRightAnswersStr: String
        get() = minPercentOfRightAnswers.toString()

}