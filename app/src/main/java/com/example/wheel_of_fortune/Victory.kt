package com.example.wheel_of_fortune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.wheel_of_fortune.databinding.FragmentVictoryBinding

//victory screen fragment class
class Victory : Fragment() {

    private lateinit var binding: FragmentVictoryBinding
    private val gameViewModel: GameViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_victory, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gameViewModel = gameViewModel

        //play button navigates back to wordguessing
        binding.victoryPlayButton.setOnClickListener(){
            Navigation.findNavController(binding.root).navigate(R.id.navigateFromVictoryToWordguessing)
        }
    }
}