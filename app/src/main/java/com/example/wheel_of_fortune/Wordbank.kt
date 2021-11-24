package com.example.wheel_of_fortune
import java.util.*

//This class function is to hold Word classes, and give one at random
class Wordbank {
    private val wordList = arrayOf(
        Word("Animals", "Cat"),
        Word("Animals", "Dog"),
        Word("Animals", "Horse"),
        Word("Animals", "Cow"),
        Word("Animals", "Tasmanian devil"),
        Word("Companies", "Tesla"),
        Word("Companies", "Tuborg"),
        Word("Companies", "Maersk"),
        Word("Companies", "Google"),
        Word("Games", "Crash Bandicoot"),
        Word("Games", "Super Mario"),
        Word("Games", "Dark Cloud"),
        Word("Games", "Street Fighter"),
        Word("Fruit", "Banana"),
        Word("Fruit", "Apple"),
        Word("Fruit", "Strawberry"),
        Word("Fruit", "Orange"),
        Word("Fruit", "Banana"),
        Word("Occupations", "Firefighter"),
        Word("Occupations", "Nurse"),
        Word("Occupations", "Software Engineer"),
        Word("Occupations", "Firefighter"),
        Word("Occupations", "Politician"),
        Word("Occupations", "Teacher"),
        Word("Phobias", "Acrophobia"),
        Word("Phobias", "Arachnophobia"),
        Word("Phobias", "Autophobia"),
        Word("Phobias", "Claustrophobia"),
        Word("Phobias", "Hydrophobia")
    )

    //this function returns a random word corresponding to the length of the wordList
    fun getRandomWord(): Word {
            val random_Number = Random().nextInt(wordList.size)
            return wordList[random_Number]
        }
}