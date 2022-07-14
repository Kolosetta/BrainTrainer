package com.example.braintrainer.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import com.example.braintrainer.R
import com.example.braintrainer.databinding.FragmentResultBinding
import com.example.braintrainer.databinding.FragmentWelcomeBinding
import com.example.braintrainer.domain.entities.GameResult
import com.example.braintrainer.domain.entities.GameSettings


class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private lateinit var gameResult: GameResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

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
            if (gameResult.isWon) {
                R.drawable.ic_happy_smile
            } else {
                R.drawable.ic_sad_smile
            }
        )

        binding.tvRequiredAnswers.text = String.format(
            requireContext().resources.getString(R.string.required_answers_label),
            gameResult.gameSettings.minCountOfRightAnswers.toString()
        )
        binding.tvActualAnswersCount.text = String.format(
            requireContext().resources.getString(R.string.your_score_label),
            gameResult.countOfRightAnswers.toString()
        )

        binding.tvRequiredPercent.text = String.format(
            requireContext().resources.getString(R.string.required_percent_answers_label),
            gameResult.gameSettings.minPercentOfRightAnswers.toString()
        )
        binding.tvActualPercent.text = String.format(
            requireContext().resources.getString(R.string.your_percent_label),
            ((gameResult.countOfRightAnswers / gameResult.countOfQuestions.toDouble()) * 100).toInt()
                .toString()
        )
    }

    private fun setupClickListeners(){
        //Переопределяем кнопку назад в активити
        //Добавляем LifecycleOwner, чтобы при удалении фрагмента, удалялся и слушатель кнопки назад
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    retryGame()
                }
            })

        binding.retryButton.setOnClickListener {
            retryGame()
        }
    }

    private fun parseArgs() {
        //Используем let потому что, getParcelable возвращает нуллабельный тип
        requireArguments().getParcelable<GameResult>(GAME_RESULT)?.let {
            gameResult = it
        }
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager
            .popBackStack(GameFragment.NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    companion object {
        private const val GAME_RESULT = "game_result"

        fun newInstance(gameResult: GameResult): ResultFragment {
            return ResultFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GAME_RESULT, gameResult)
                }
            }
        }
    }

}