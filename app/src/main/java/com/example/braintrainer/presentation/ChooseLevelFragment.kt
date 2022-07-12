package com.example.braintrainer.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
    }

    companion object {

        const val NAME = "choose_level"

        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }

}