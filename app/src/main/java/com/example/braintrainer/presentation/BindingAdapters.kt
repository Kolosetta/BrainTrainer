package com.example.braintrainer.presentation

import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.braintrainer.R

interface OnAnswerClickListener{
    fun onAnswerClick(answer: Int)
}

@BindingAdapter("requiredAnswers")
fun bindRequireAnswers(textView: TextView, count: Int){
    textView.text = String.format(
        textView.context.getString(R.string.required_answers_label),
        count
    )
}

@BindingAdapter("actualResultAnswers")
fun bindResultAnswers(textView: TextView, count: Int){
    textView.text = String.format(
        textView.context.getString(R.string.your_score_label),
        count
    )
}

@BindingAdapter("requiredPercent")
fun bindRequiredPercent(textView: TextView, percent: Int){
    textView.text = String.format(
        textView.context.getString(R.string.required_percent_answers_label),
        percent
    )
}

@BindingAdapter("actualResultPercent")
fun bindResultPercent(textView: TextView, percent: Int){
    textView.text = String.format(
        textView.context.getString(R.string.your_percent_label),
        percent
    )
}

@BindingAdapter("resultSmileImage")
fun binResultSmileImage(imageView: ImageView, isWon: Boolean){
    imageView.setImageResource(
        if (isWon) {
            R.drawable.ic_happy_smile
        } else {
            R.drawable.ic_sad_smile
        }
    )
}

//Адаптеры для GameFragment
//Биндит правильнео кол-во ответов
@BindingAdapter("gameProgress")
fun bindGameProgress(progressBar: ProgressBar, progress: Int){
    progressBar.setProgress(progress, true)
}

//Биндит правильный текущий цвет строке количества ответов
@BindingAdapter("enoughCountColorChanger")
fun bindEnoughCountColor(textView: TextView, enoughCount: Boolean){
    val colorId = if(enoughCount){
        android.R.color.holo_green_light
    }
    else{
        android.R.color.holo_red_light
    }
    val color = ContextCompat.getColor(textView.context, colorId)
    textView.setTextColor(color)
}

//Биндит правильный текущий цвет прогресс бару
@BindingAdapter("enoughPercentColorChanger")
fun bindEnoughPercentColor(progressBar: ProgressBar, enoughPercent: Boolean){
    val colorId = if(enoughPercent){
        android.R.color.holo_green_light
    }
    else{
        android.R.color.holo_red_light
    }
    val color = ContextCompat.getColor(progressBar.context, colorId)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int){
    textView.text = number.toString()
}

//Устанавливает слушатели кликов. Сюда необходимо передать метод,
//который примет инт и ничего не вернет. В частном случае - метод CheckAnswer
@BindingAdapter("onAnswerClickListener")
fun bindOnAnswerClickListener(textView: TextView, clickListener: OnAnswerClickListener){
    textView.setOnClickListener {
        clickListener.onAnswerClick(textView.text.toString().toInt())
    }
}