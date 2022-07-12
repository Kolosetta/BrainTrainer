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
        //Переопределяем кнопку назад в активити
        //Добавляем LifecycleOwner, чтобы при удалении фрагмента, удалялся и слушатель кнопки назад
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                retryGame()
            }
        })
        binding.retryButton.setOnClickListener{
            retryGame()
        }
    }

    private fun parseArgs(){
        //Используем let потому что, getParcelable возвращает нуллабельный тип
        requireArguments().getParcelable<GameResult>(GAME_RESULT)?.let{
            gameResult = it
        }
    }

    private fun retryGame(){
        requireActivity().supportFragmentManager
            .popBackStack(GameFragment.NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }


    companion object{

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