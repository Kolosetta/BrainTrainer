package com.example.braintrainer.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.braintrainer.databinding.FragmentGameBinding
import com.example.braintrainer.domain.entities.GameResult

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding

    //Получение аргументов из класса GameFragmentArgs
    private val args by navArgs<GameFragmentArgs>()

    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(
            this,
            AndroidViewModelFactory.getInstance(requireActivity().application)
        )[GameViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setupScreen()
        viewModel.startGame(args.level)
    }

    private fun setupScreen(){
        viewModel.gameResult.observe(viewLifecycleOwner){
            launchResultFragment(it)
        }
    }


    private fun launchResultFragment(gameResult: GameResult){
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToResultFragment(gameResult)
        )
    }
}