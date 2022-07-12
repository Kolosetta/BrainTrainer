package com.example.braintrainer.domain.entities

data class Question(
    val sum: Int,
    val visibleNumber: Int,
    val answers: List<Int>
) {
    val rightAnswer: Int
    get() = sum - visibleNumber
}