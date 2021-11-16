package com.example.wheel_of_fortune

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//this is the viewmodel for the game. observer pattern is implemented by way of LiveData, and databinding here
//it is also here the model classes are initiated, following the app architecture of MVVM.
class GameViewModel: ViewModel() {

    //initiating models and livedata
    private var wordbank = Wordbank()
    private var player = Player(0, 5)
    private var wheel = Wheel()
    private var currentWordClass: Word = wordbank.getRandomWord()
    private val _currentGenre =  MutableLiveData(currentWordClass.getWordGenre())
    val currentGenre: LiveData<String>
        get() = _currentGenre
    private val _recentSpinResult = MutableLiveData(wheel.getRecentSpinResult())
    val recentSpinResult: LiveData<String>
        get() = _recentSpinResult
    private val _points = MutableLiveData(player.getPoints())
    val points: LiveData<Int>
        get() = _points
    private val _life = MutableLiveData(player.getLife())
    val life: LiveData<Int>
        get() = _life
    private val _lastResult =  MutableLiveData(0)
    val lastResult: LiveData<Int>
        get() = _lastResult

    //changes player life and updates livedata
    fun changePlayerlife(value: Int){
        player.changeLives(value)
        _life.value = player.getLife()
    }
    //resets the viewmodel values. used at the end of the game, and when you quit.
    fun resetGame(){
        _lastResult.value = player.getPoints()
        player = Player(0, 5)
        wheel = Wheel()
        currentWordClass = wordbank.getRandomWord()
        _currentGenre.value =  currentWordClass.getWordGenre()
        _recentSpinResult.value = wheel.getRecentSpinResult()
        _points.value = player.getPoints()
        _life.value = player.getLife()
    }

    //changes hidden word string index to specified character
    fun changeHiddenWordStringIndex(index: Int, newChar: Char){
        currentWordClass.setHiddenWordStringIndex(index, newChar)
    }

    //changes player points by specified amount and updates livedata
    fun changePlayerPoints(value: Int){
        player.changePoints(value)
        _points.value = player.getPoints()
    }

    //returns true if player model has lives. false otherwise
    fun playerHasLives(): Boolean {
        return player.lives > 0
    }

    //returns true if all letters of hiddenword are guessed. false otherwise
    fun wordsAreGuessed(): Boolean {
        var bool = true
        for (i in currentWordClass.getHiddenWordString()) {
            if (i == '#'){
                bool = false
            }
        }
        return bool
    }

    //given a character, this function checks if it is guessed. first it goes through the wordstring.
    // if it is not already guessed, the number of letters guessed is noted, and player points is adjusted at the end
    fun submitGuess(guess: Char){
        var lettersGuessed = 0
        if (guess != '#') {
            var index = 0
            for (i in currentWordClass.getWordString()) {
                if (guess.equals(i, ignoreCase = true)) {
                    if (currentWordClass.getHiddenWordString()[index] == '#') {
                        lettersGuessed++
                        changeHiddenWordStringIndex(index, guess)
                    }
                }
                index++
            }
        }
            changePlayerPoints(lettersGuessed * wheel.getRecentSpin())
            if (lettersGuessed == 0) {
                changePlayerlife(-1)
            }
    }

    //sets player points to specified integer
    fun setPlayerPoints(value: Int){
        player.setPoints(value)
        _points.value = player.getPoints()
    }

    //returns wheel value of a spin and updates livedata
    fun spinWheel(): Int {
        val value =  wheel.getRandomValue()
        _recentSpinResult.value = wheel.getRecentSpinResult()
        return value
    }

    //returns hidden word
    fun getHiddenWord(): String {
        return currentWordClass.getHiddenWordString()
    }

}