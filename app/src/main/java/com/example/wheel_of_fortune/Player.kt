package com.example.wheel_of_fortune

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