package com.example.braintrainer.domain.usecases

import com.example.braintrainer.domain.entities.Question
import com.example.braintrainer.domain.repository.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository) {

    //Переопределение invoke, чтобы usecase можно было вызывать как метод
    operator fun invoke(maxSumValue: Int): Question{
        return repository.generateQuestion(maxSumValue, COUNT_OF_ANSWERS)
    }

    companion object{
        private const val COUNT_OF_ANSWERS = 6
    }

}