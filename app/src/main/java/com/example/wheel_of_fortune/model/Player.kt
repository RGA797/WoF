package com.example.wheel_of_fortune.model

//this class holds a players lives and points, and functions to get and change them.
//as it takes parameters, the viewmodel can set it at will. for this game, it will be 0 points and 5 lives at the start.
class Player(private var points: Int, var lives: Int){

    fun changeLives(value: Int) {
        this.lives += value
    }

    fun changePoints(value: Int) {
        this.points += value
    }

    fun getPoints(): Int {
        return points
    }

    fun setPoints(points: Int) {
        this.points = points
    }

    fun getLife(): Int {
        return this.lives
    }
}