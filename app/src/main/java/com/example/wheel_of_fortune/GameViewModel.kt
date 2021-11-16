package com.example.wheel_of_fortune

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//this is the viewmodel for the game. observer pattern is implemented by way of LiveData, and databinding here
//it is also here the model classes are initiated, following the app architecture of MVVM
class GameViewModel: ViewModel() {
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


    fun changePlayerlife(value: Int){
        player.changeLives(value)
        _life.value = player.getLife()
    }

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

    fun changeHiddenWordStringIndex(index: Int, newChar: Char){
        currentWordClass.setHiddenWordStringIndex(index, newChar)
    }

    fun changePlayerPoints(value: Int){
        player.changePoints(value)
        _points.value = player.getPoints()
    }
    fun playerHasLives(): Boolean {
        return player.lives > 0
    }

    fun wordsAreGuessed(): Boolean {
        var bool = true
        for (i in currentWordClass.getHiddenWordString()) {
            if (i == '#'){
                bool = false
            }
        }
        return bool
    }

    fun submitGuess(guess: Char){
        var lettersGuessed: Int = 0
        if (guess != '#') {
            var j = 0
            for (i in currentWordClass.getWordString()) {
                if (guess.equals(i, ignoreCase = true)) {
                    if (currentWordClass.getHiddenWordString()[j] == '#') {
                        lettersGuessed++
                        changeHiddenWordStringIndex(j, guess)
                    }
                }
                j++
            }
        }
            changePlayerPoints(lettersGuessed * wheel.getRecentSpin())
            if (lettersGuessed == 0) {
                changePlayerlife(-1)
            }
    }

    fun setPlayerPoints(value: Int){
        player.setPoints(value)
        _points.value = player.getPoints()
    }

    fun spinWheel(): Int {
        val value =  wheel.getRandomValue()
        _recentSpinResult.value = wheel.getRecentSpinResult()
        return value
    }

    fun getHiddenWord(): String {
        return currentWordClass.getHiddenWordString()
    }

}