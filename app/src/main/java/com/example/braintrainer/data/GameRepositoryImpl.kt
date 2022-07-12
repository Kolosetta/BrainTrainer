package com.example.braintrainer.data

import com.example.braintrainer.domain.entities.GameSettings
import com.example.braintrainer.domain.entities.Level
import com.example.braintrainer.domain.entities.Question
import com.example.braintrainer.domain.repository.GameRepository
import kotlin.random.Random

object  GameRepositoryImpl: GameRepository {

    //Минимальное значение, которое будет сгенерировано
    private const val MIN_SUM_VALUE = 2
    private const val MIN_VISIBLE_VALUE = 1

    override fun generateQuestion(maxSumValue: Int, countOfAnswers: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_VISIBLE_VALUE, sum) //Открытое игроку число

        //Добавляе правильный ответ и неправильные
        val answers = HashSet<Int>() //HashSet выбран, чтобы значения ответов не повторялись
        val rightAnswer = sum - visibleNumber
        answers.add(rightAnswer)
        while(answers.size < countOfAnswers){
            answers.add(Random.nextInt(MIN_VISIBLE_VALUE, maxSumValue))
        }
        //Возвращаем список ответов в перемешанном листе
        return Question(sum, visibleNumber, answers.toList().shuffled())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when(level){
            Level.TEST -> GameSettings(10,3,50, 10)
            Level.EASY -> GameSettings(15,10,70, 60)
            Level.NORMAL -> GameSettings(30,20,80, 50)
            Level.HARD -> GameSettings(100,30,90, 40)
        }
    }

}