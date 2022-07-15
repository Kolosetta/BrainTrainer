package com.example.braintrainer.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.navigation.fragment.findNavController
import com.example.braintrainer.R
import com.example.braintrainer.databinding.FragmentGameBinding
import com.example.braintrainer.domain.entities.GameResult
import com.example.braintrainer.domain.entities.GameSettings
import com.example.braintrainer.domain.entities.Level

class GameFragment : Fragment() {

    private lateinit var level: Level
    private lateinit var binding: FragmentGameBinding

    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(
            this,
            AndroidViewModelFactory.getInstance(requireActivity().application)
        )[GameViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.answer1.setOnClickListener{
            launchResultFragment(GameResult(true, 10,20,
                GameSettings(10,10,10,10)))
        }
        setupScreen()
        setupBtnListeners()
        viewModel.startGame(level)
    }

    private fun setupBtnListeners(){
        binding.answer1.setOnClickListener {
            val answer = (it as TextView).text.toString().toInt()
            viewModel.checkAnswer(answer)
        }
        binding.answer2.setOnClickListener {
            val answer = (it as TextView).text.toString().toInt()
            viewModel.checkAnswer(answer)
        }
        binding.answer3.setOnClickListener {
            val answer = (it as TextView).text.toString().toInt()
            viewModel.checkAnswer(answer)
        }
        binding.answer4.setOnClickListener {
            val answer = (it as TextView).text.toString().toInt()
            viewModel.checkAnswer(answer)
        }
        binding.answer5.setOnClickListener {
            val answer = (it as TextView).text.toString().toInt()
            viewModel.checkAnswer(answer)
        }
        binding.answer6.setOnClickListener {
            val answer = (it as TextView).text.toString().toInt()
            viewModel.checkAnswer(answer)
        }
    }

    private fun setupScreen(){
        viewModel.timeLeft.observe(viewLifecycleOwner){
            binding.tvTimer.text = it
        }
        viewModel.question.observe(viewLifecycleOwner){
            with(binding) {
                tvSum.text = it.sum.toString()
                leftNumber.text = it.visibleNumber.toString()
                answer1.text = it.answers[0].toString()
                answer2.text = it.answers[1].toString()
                answer3.text = it.answers[2].toString()
                answer4.text = it.answers[3].toString()
                answer5.text = it.answers[4].toString()
                answer6.text = it.answers[5].toString()
            }
        }
        viewModel.countOfRightAnswersStr.observe(viewLifecycleOwner){
            binding.tvProgress.text = it.toString()
        }
        viewModel.percentsOfRightAnswers.observe(viewLifecycleOwner){
            binding.progressBar.setProgress(it, true)
        }
        viewModel.enoughCountOfRightAnswers.observe(viewLifecycleOwner){
            val colorId = if(it){
                android.R.color.holo_green_light
            }
            else{
                android.R.color.holo_red_light
            }
            val color = ContextCompat.getColor(requireContext(), colorId)
            binding.tvProgress.setTextColor(color)
        }
        viewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner){
            val colorId = if(it){
                android.R.color.holo_green_light
            }
            else{
                android.R.color.holo_red_light
            }
            val color = ContextCompat.getColor(requireContext(), colorId)
            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }
        viewModel.secondaryPercent.observe(viewLifecycleOwner){
            binding.progressBar.secondaryProgress = it
        }
        viewModel.gameResult.observe(viewLifecycleOwner){
            launchResultFragment(it)
        }
    }


    private fun parseArgs(){
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }

    private fun launchResultFragment(gameResult: GameResult){
        val args = Bundle().apply {
            putParcelable(ResultFragment.GAME_RESULT, gameResult)
        }
        findNavController().navigate(R.id.action_gameFragment_to_resultFragment, args)
    }

    companion object {
        const val KEY_LEVEL = "level"
        const val NAME = "game_fragment"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }

}