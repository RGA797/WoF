package com.example.wheel_of_fortune
import java.util.*

class Wordbank {
    private val wordList = arrayOf(
        Word("Animals", "cat"),
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
        Word("Phobias", "acrophobia"),
        Word("Phobias", "arachnophobia"),
        Word("Phobias", "autophobia"),
        Word("Phobias", "claustrophobia"),
        Word("Phobias", "hydrophobia")
    )
    fun getRandomWord(): Word {
            val random_Number = Random().nextInt(wordList.size)
            return wordList[random_Number]
        }
}