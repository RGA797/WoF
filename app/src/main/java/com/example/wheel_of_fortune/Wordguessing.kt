package com.example.wheel_of_fortune

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wheel_of_fortune.databinding.FragmentWordguessingBinding

class Wordguessing : Fragment() {

    private lateinit var binding: FragmentWordguessingBinding
    private val gameViewModel: GameViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wordguessing, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gameViewModel = gameViewModel

        //set as lifecycle owner, to update livedata automatically.
        binding.lifecycleOwner = viewLifecycleOwner
        //setting visibility of buttons
        binding.editText.visibility = INVISIBLE
        binding.enterButton.visibility = INVISIBLE

        //logic for when spinButoon is pressed. depending on the values, it may make it invisible
        binding.spinButton.setOnClickListener{ onSpin() }

        //logic for enterButton after it is clicked, it will become invisible and possibly make words visible
        binding.enterButton.setOnClickListener{ onEnter() }

        //quit button sends you to the menu fragment
        binding.quitButton.setOnClickListener{ onQuit() }

        updateRecycler()
    }



    fun onEnter() {
        if (!TextUtils.isEmpty(binding.editText.text)) {
            gameViewModel.submitGuess(binding.editText.text[0])
            hideKeyboard()
            binding.spinButton.visibility = VISIBLE
            binding.editText.visibility = INVISIBLE
            binding.enterButton.visibility = INVISIBLE
            if (!gameViewModel.playerHasLives()) {
                gameViewModel.resetGame()
                Navigation.findNavController(binding.root).navigate(R.id.navigateToGameover)
            } else if (gameViewModel.wordsAreGuessed()) {
                gameViewModel.resetGame()
                Navigation.findNavController(binding.root).navigate(R.id.navigateToVictory)
            }
            updateRecycler()
        }
    }

    fun onQuit(){
        gameViewModel.resetGame()
        updateRecycler()
        Navigation.findNavController(binding.root).navigate(R.id.navigateToMenu)
    }


    fun onSpin(){
        val wheelValue = gameViewModel.spinWheel()

        if (wheelValue > 0) {
            binding.spinButton.visibility = INVISIBLE
            binding.editText.visibility = VISIBLE
            binding.enterButton.visibility = VISIBLE
        }
        if (wheelValue == -1){
            gameViewModel.changePlayerlife(1)
        }
        if (wheelValue == -2){
            gameViewModel.changePlayerlife(-1)
            if (!gameViewModel.playerHasLives()){
                Navigation.findNavController(binding.root).navigate(R.id.navigateToGameover)
            }
        }
        if (wheelValue == -3){
            gameViewModel.setPlayerPoints(0)
        }
    }

    //code for hiding keyboard gotten from stackoverflow https://stackoverflow.com/questions/41790357/close-hide-the-android-soft-keyboard-with-kotlin
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        binding.editText.getText().clear()
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun updateRecycler(){
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            val list = listOf(gameViewModel.getHiddenWord())
            adapter = RecyclerViewAdapter(list, context)
        }
    }
}
