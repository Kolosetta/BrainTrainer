package com.example.braintrainer.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.braintrainer.R
import com.example.braintrainer.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private val args by navArgs<ResultFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupScreen()
    }

    private fun setupScreen() {
        binding.smileResult.setImageResource(
            if (args.gameResult.isWon) {
                R.drawable.ic_happy_smile
            } else {
                R.drawable.ic_sad_smile
            }
        )

        binding.tvRequiredAnswers.text = String.format(
            requireContext().resources.getString(R.string.required_answers_label),
            args.gameResult.gameSettings.minCountOfRightAnswers.toString()
        )
        binding.tvActualAnswersCount.text = String.format(
            requireContext().resources.getString(R.string.your_score_label),
            args.gameResult.countOfRightAnswers.toString()
        )

        binding.tvRequiredPercent.text = String.format(
            requireContext().resources.getString(R.string.required_percent_answers_label),
            args.gameResult.gameSettings.minPercentOfRightAnswers.toString()
        )
        binding.tvActualPercent.text = String.format(
            requireContext().resources.getString(R.string.your_percent_label),
            ((args.gameResult.countOfRightAnswers / args.gameResult.countOfQuestions.toDouble()) * 100)
                .toInt().toString()
        )
    }

    private fun setupClickListeners(){
        binding.retryButton.setOnClickListener {
            retryGame()
        }
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }

}