package com.example.braintrainer.domain.repository

import com.example.braintrainer.domain.entities.GameSettings
import com.example.braintrainer.domain.entities.Level
import com.example.braintrainer.domain.entities.Question

interface GameRepository {
    fun generateQuestion(maxSumValue: Int, countOfAnswers: Int): Question
    fun getGameSettings(level: Level): GameSettings
}