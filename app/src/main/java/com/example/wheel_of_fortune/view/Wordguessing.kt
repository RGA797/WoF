package com.example.wheel_of_fortune.view

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
import com.example.wheel_of_fortune.R
import com.example.wheel_of_fortune.adapter.RecyclerViewAdapter
import com.example.wheel_of_fortune.databinding.FragmentWordguessingBinding
import com.example.wheel_of_fortune.viewModel.GameViewModel

//wordguessing screen fragment class. this is the class with most of the view implementations
class Wordguessing : Fragment() {

    private lateinit var binding: FragmentWordguessingBinding
    //viewmodel is made here. using activitymodels for the instantiation, it can be shared among other fragments
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

        //recyclerview updated
        updateRecycler()
    }

    //logic for enter button
    fun onEnter() {
        if (!TextUtils.isEmpty(binding.editText.text)) {
            //takes entered letter of edittext and uses viewmodel submitguess method. visibility of
            gameViewModel.submitGuess(binding.editText.text[0])
            hideKeyboard()
            //enter button and edittext made invisible after being pressed. spin button is made visible
            binding.spinButton.visibility = VISIBLE
            binding.editText.visibility = INVISIBLE
            binding.enterButton.visibility = INVISIBLE


            //if the player has lost or won, the game is reset, and a we used navigation to go to other fragments
            if (!gameViewModel.playerHasLives()) {
                gameViewModel.resetGame()
                Navigation.findNavController(binding.root).navigate(R.id.navigateToGameover)
            } else if (gameViewModel.wordsAreGuessed()) {
                gameViewModel.resetGame()
                Navigation.findNavController(binding.root).navigate(R.id.navigateToVictory)
            }

            //recyclerview updated
            updateRecycler()
        }
    }

    //quit button logic. game is reset and we navigate to menu
    fun onQuit(){
        gameViewModel.resetGame()
        updateRecycler()
        Navigation.findNavController(binding.root).navigate(R.id.navigateToMenu)
    }

    //spin button logic
    fun onSpin(){

        //viewmodel spinwheel model returns a spin result
        val wheelValue = gameViewModel.spinWheel()


        //depending on the result, a player can either guess, lose/gain a life or go Bankrupt.
        if (wheelValue > 0) {
            //spin button made invisible, but the player can now guess using edittext and submit button
            binding.spinButton.visibility = INVISIBLE
            binding.editText.visibility = VISIBLE
            binding.enterButton.visibility = VISIBLE
        }

        //player gains a life
        if (wheelValue == -1){
            gameViewModel.changePlayerlife(1)
        }

        //player loses a life
        if (wheelValue == -2){
            gameViewModel.changePlayerlife(-1)
            if (!gameViewModel.playerHasLives()) {
                gameViewModel.resetGame()
                Navigation.findNavController(binding.root).navigate(R.id.navigateToGameover)
            }
        }

        //player goes bankrupt
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

    //this function should update the recyclerview. this doesnt use databinding, and is done manually. there may be a better way to do this, but none was found.
    //the recyclerview uses hiddenword as per the requirements before NFR_5 changes before the changes on november 22th.
    //significant time and effort has ben spent implementing this, so this will not be changed.
    fun updateRecycler(){
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            val list = listOf(gameViewModel.getHiddenWord())
            adapter = RecyclerViewAdapter(list, context)
        }
    }
}
