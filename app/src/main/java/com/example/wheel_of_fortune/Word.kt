package com.example.wheel_of_fortune

class Word(val genre: String, val word_string: String){

    private var hiddenWord = createHiddenWord()

    fun getWordGenre(): String {
        return genre
    }

    //made with help from: https://www.techiedelight.com/replace-character-specific-index-string-kotlin/
    fun setHiddenWordStringIndex(index: Int, newChar: Char){
        if (index>-1 && index < word_string.length){
            val chars = hiddenWord.toCharArray()
            chars[index] = newChar
            hiddenWord = String(chars)
        }
    }

    fun createHiddenWord(): String{
        var hiddenString: String = ""
        for (i in word_string)
            if (i.isLetter()) {
                hiddenString = hiddenString.plus('#')
            }
            else{
                hiddenString = hiddenString.plus(' ')
            }
        return hiddenString
    }

    fun getHiddenWordString(): String {
        return hiddenWord
    }

    fun getWordString(): String {
        return word_string
    }
}
