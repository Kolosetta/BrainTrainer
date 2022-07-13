package com.example.braintrainer.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.braintrainer.R
import com.example.braintrainer.databinding.FragmentGameBinding
import com.example.braintrainer.domain.entities.GameResult
import com.example.braintrainer.domain.entities.GameSettings
import com.example.braintrainer.domain.entities.Level

class GameFragment : Fragment() {

    private lateinit var level: Level
    private lateinit var viewModel: GameFragmentViewModel
    private lateinit var binding: FragmentGameBinding


    private var maxSumValue: Int? = null

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
        viewModel = ViewModelProvider(this)[GameFragmentViewModel::class.java]
        binding.answer1.setOnClickListener{
            launchResultFragment(GameResult(true, 10,20,
                GameSettings(10,10,10,10)))
        }
        setupScreen()
        setupQuestion()
    }

    private fun setupScreen(){
        viewModel.startGame(level)
    }

    private fun setupQuestion(){

    }

    private fun parseArgs(){
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }

    private fun launchResultFragment(gameResult: GameResult){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, ResultFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        private const val KEY_LEVEL = "level"
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