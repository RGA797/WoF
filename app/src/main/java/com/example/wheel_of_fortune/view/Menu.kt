package com.example.wheel_of_fortune.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.wheel_of_fortune.R


class Menu : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        val playButton = view.findViewById<Button>(R.id.playButton)
        playButton.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.navigateToWordguessing)
        }
        return view
    }

}