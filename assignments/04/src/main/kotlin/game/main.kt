package game

fun main(args: Array<String>) {
    Gui.greeting()
    do {
        val guessed_word = Words.getWord()
        Gui.starting(guessed_word.length)

        println(guessed_word) // ubrat

        var tries = 0
        while (tries < 10) {
            val new_word = Gui.get_word(guessed_word.length)
            val res = new_word.countBullsCows(guessed_word)
            if (res.first == guessed_word.length) {
                break
            }
            Gui.printBullsCows(res)
            tries++
        }
        if (tries == 10)
            Gui.loose(guessed_word)
        else
            Gui.congratulations(tries + 1)
    } while (Gui.restart())
}