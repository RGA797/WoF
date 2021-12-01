package com.example.wheel_of_fortune.model

import java.util.*

//this class is a model class that gives a random "spin" result.
//it also keeps track of the last spin "recentSpin" and has corresponding strings to give for UI.
class Wheel {
    //wheel of fortune values in an array. positive numbers are points, -1 means gain a life,-2 means lose a life, -3 means bankrupt (lose all points).
    private var wheel = intArrayOf(-2, 800, 1000, 500, 100, 800, 1000, 500, 600, 800, -1, 600, 500, 100, 1500, -3, 800, 500, 600, 500, -1, -2)
    private var recentSpin: Int = 0
    private var recentSpinResult = ""


    //this function returns a random spin of wheel. The recent spin and
    fun getRandomValue(): Int {
            val wheel_length = wheel.size
            val random_Number = Random().nextInt(wheel_length-1)
            val spin = wheel[random_Number]
            recentSpin = spin
            setRecentSpinResult()
            return spin
        }

    fun getRecentSpin(): Int{
        return recentSpin
    }

    //sets the spin result string.
    fun setRecentSpinResult() {
        if (recentSpin == -1){
            recentSpinResult = "You Gained a life!"
        }
        else if (recentSpin == -2){
            recentSpinResult = "You lost a life"
        }

        else if (recentSpin == -3){
            recentSpinResult = "Bancrupt! you lost your points."
        }
        else{
            recentSpinResult = recentSpin.toString()
        }
    }

    fun getRecentSpinResult(): String {
        return recentSpinResult
    }
}