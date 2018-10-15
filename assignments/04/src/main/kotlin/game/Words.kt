package game

import java.io.File
import java.util.Random

class Words {
    companion object {
        val words = File("dictionary.txt").readText()
                .split("\n")
                .map { it.filter { it.isLetter() } }
        fun getWord() = words[Random().nextInt(words.size - 1)]
    }
}