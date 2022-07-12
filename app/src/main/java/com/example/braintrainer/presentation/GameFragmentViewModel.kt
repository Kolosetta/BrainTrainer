package com.example.braintrainer.presentation

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.braintrainer.R
import com.example.braintrainer.data.GameRepositoryImpl
import com.example.braintrainer.domain.entities.GameSettings
import com.example.braintrainer.domain.entities.Level
import com.example.braintrainer.domain.entities.Question
import com.example.braintrainer.domain.usecases.GenerateQuestionUseCase
import com.example.braintrainer.domain.usecases.GetGameSettingsUseCase

class GameFragmentViewModel(application: Application) : AndroidViewModel(application) {

    //TODO Заменить DI, чтобы не нарушать чистую архитектуру
    private val repository = GameRepositoryImpl
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)
    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val context = application

    private lateinit var gameSettings: GameSettings
    private var timer: CountDownTimer? = null
    private var countOfRightAnswers = 0
    private var countOfQuestions = 0

    //TODO Закончил на 20:00 6.9

    //Хранит процент правильных ответов
    private val _percentsOfRightAnswers = MutableLiveData<Int>()
    val percentsOfRightAnswers: LiveData<Int>
        get() = _percentsOfRightAnswers

    //Хранит кол-во правильных ответов
    private val _countOfRightAnswers = MutableLiveData<String>()
    val countOfRightAnswersLD: LiveData<String>
        get() = _countOfRightAnswers

    //Хранит bool, достаточно ли ответов для победы
    private val _enoughCountOfRightAnswers = MutableLiveData<Boolean>()
    val enoughCountOfRightAnswers: LiveData<Boolean>
        get() = _enoughCountOfRightAnswers

    //Хранит bool, достаточный ли процент правильных ответов для победы
    private val _enoughPercentOfRightAnswers = MutableLiveData<Boolean>()
    val enoughPercentOfRightAnswers: LiveData<Boolean>
        get() = _enoughPercentOfRightAnswers

    //Хранит текущий вопрос с вариантами ответов
    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    //Хранит в форматированной строке оставшееся время
    private val _timeLeft = MutableLiveData<String>()
    val timeLeft: LiveData<String>
        get() = _timeLeft


    fun startGame(level: Level){
        gameSettings = getGameSettingsUseCase.invoke(level)
        startTimer()
        generateQuestion()
    }

    //Стартует таймер продолжительностью в длительность игры в gameSettings
    private fun startTimer(){
        timer = object : CountDownTimer(gameSettings.gameDuration * MILLSEC_IN_SEC, MILLSEC_IN_SEC){
            override fun onTick(millsecsLeft: Long) {
                _timeLeft.value = formatTime(millsecsLeft)
            }

            override fun onFinish() {
                finishGame()
            }
        }
        timer?.start()
    }

    //Генерирует вопрос и кладет в LiveData
    fun generateQuestion(){
        _question.value = generateQuestionUseCase.invoke(gameSettings.maxSumValue)
    }

    //Проверяет правильность ответа, увеличивает счетчики
    fun checkAnswer(answer: Int){
        val rightAnswer = question.value?.rightAnswer
        if(answer == rightAnswer){
            countOfRightAnswers++
        }
        countOfQuestions++
        updateProgress()
        generateQuestion()
    }

    //Люновляет процент и кол-во правильных ответов
    private fun updateProgress(){
        val progressPercent = ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt() //В процентах
        _percentsOfRightAnswers.value = progressPercent
        _countOfRightAnswers.value = String.format(
            context.resources.getString(R.string.answers_progress),
            countOfRightAnswers,
            gameSettings.minCountOfRightAnswers
        )
    }

    //Завершает игру
    private fun finishGame(){

    }

    //Преобразует миллисекунды в форматированную строку
    private fun formatTime(millsecs: Long): String{
        var seconds = millsecs / 1000
        val minutes = seconds / SECONDS_IN_MINUTE
        seconds -= millsecs * SECONDS_IN_MINUTE
        return String.format("%02d:%02d", minutes, seconds)
    }

    //Вызывается при выходе из фрагмента и останавливает таймер
    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    companion object{
        const val MILLSEC_IN_SEC = 1000L
        const val SECONDS_IN_MINUTE = 60
    }
}