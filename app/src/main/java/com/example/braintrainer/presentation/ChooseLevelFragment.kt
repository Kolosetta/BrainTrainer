package com.example.braintrainer.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.braintrainer.R
import com.example.braintrainer.databinding.FragmentChooseLevelBinding
import com.example.braintrainer.databinding.FragmentGameBinding
import com.example.braintrainer.domain.entities.Level

class ChooseLevelFragment : Fragment() {

    private lateinit var binding: FragmentChooseLevelBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            buttonTest.setOnClickListener{
                startGameFragment(Level.TEST)
            }

            buttonEasy.setOnClickListener{
                startGameFragment(Level.EASY)
            }

            buttonNormal.setOnClickListener{
                startGameFragment(Level.NORMAL)
            }

            buttonHard.setOnClickListener{
                startGameFragment(Level.HARD)
            }
        }

    }

    private fun startGameFragment(level: Level){
        val args = Bundle().apply {
            putParcelable(GameFragment.KEY_LEVEL, level)
        }
        findNavController().navigate(R.id.action_chooseLevelFragment_to_gameFragment, args)
    }

    companion object {

        const val NAME = "choose_level"

        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }

}