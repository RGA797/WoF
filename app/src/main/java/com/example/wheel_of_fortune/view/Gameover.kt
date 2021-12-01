package com.example.wheel_of_fortune.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.wheel_of_fortune.R
import com.example.wheel_of_fortune.databinding.FragmentGameoverBinding
import com.example.wheel_of_fortune.viewModel.GameViewModel


//game over fragmens class.
class Gameover : Fragment() {

    private lateinit var binding: FragmentGameoverBinding
    private val gameViewModel: GameViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gameover, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.gameViewModel = gameViewModel


        binding.gameoverPlayAgainButton.setOnClickListener(){
            Navigation.findNavController(binding.root).navigate(R.id.navigateFromGameoverToWordguessing)
        }

    }



}