package game

class Gui {
    companion object {
        fun greeting() {
            println("Welcome to Bulls and Cows game")
        }
        fun restart(): Boolean {
            println("Do you want to play new game (y/n)")
            val ans = readLine()?.toLowerCase()
            if (ans == null) {
                System.exit(0)
            }
            return when (ans) {
                "y" -> true
                "n" -> false
                else -> restart()
            }
        }
        fun congratulations(tries: Int) {
            println("Well done!\nSpent attempts: $tries")
        }
        fun loose(word: String) {
            println("You loose:(\nThe word is $word")
        }
        fun which_word(chars: Int) {
            println("Input $chars-letter word")
        }
        fun printBullsCows(res: Pair<Int, Int>) {
            println("Bulls: ${res.first}\nCows: ${res.second}")
        }
        fun starting(chars: Int) {
            println("I made a $chars-letter word, try to guess")
        }
        fun get_word(len: Int): String {
            print("> ")
            var new_word = readLine()?.toLowerCase()
            if (new_word == null)
                System.exit(0)
            while (!new_word!!.checkword(len)) {
                Gui.which_word(len)
                new_word = readLine()?.toLowerCase()
                if (new_word == null)
                    System.exit(0)
            }
            return new_word
        }
    }
}