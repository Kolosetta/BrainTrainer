package com.example.braintrainer.domain.usecases

import com.example.braintrainer.domain.entities.GameSettings
import com.example.braintrainer.domain.entities.Level
import com.example.braintrainer.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level): GameSettings{
        return repository.getGameSettings(level)
    }

}