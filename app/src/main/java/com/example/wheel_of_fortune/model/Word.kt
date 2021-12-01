package com.example.wheel_of_fortune.model


//Model class, used to keep track of wordstrings and their corresponding genre and hiddenWordString
class Word(val genre: String, val word_string: String){

    private var hiddenWord = createHiddenWord()

    fun getWordGenre(): String {
        return genre
    }

    //this function sets a specific character index of hiddenWord
    //made with help from: https://www.techiedelight.com/replace-character-specific-index-string-kotlin/
    fun setHiddenWordStringIndex(index: Int, newChar: Char){
        if (index>-1 && index < word_string.length){
            val chars = hiddenWord.toCharArray()
            chars[index] = newChar
            hiddenWord = String(chars)
        }
    }

    //every Word class has a hidden word string corresponding to a series of '#' matching the wordstring.
    //this is done with the following function that returns hiddenString
    fun createHiddenWord(): String{
        var hiddenString: String = ""
        for (i in word_string) {
            if (i.isLetter()) {
                hiddenString = hiddenString.plus('#')
            } else {
                hiddenString = hiddenString.plus(' ')
            }
        }
        return hiddenString
    }

    fun getHiddenWordString(): String {
        return hiddenWord
    }

    fun getWordString(): String {
        return word_string
    }

    fun setHiddenWord(string: String){
        hiddenWord = string
    }
}
