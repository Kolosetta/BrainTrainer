package com.example.braintrainer.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.braintrainer.R

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
