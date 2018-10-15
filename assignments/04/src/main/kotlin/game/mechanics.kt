package game

fun String.checkword(len: Int): Boolean = this.length == len && this.all { it.isLetter() }

fun String.countBullsCows(word: String): Pair<Int, Int> {
    val wordlist = word.toMutableList()
    val list = this.toMutableList()
    val bulls = this.zip(word).count {
        if (it.first == it.second) {
            list.remove(it.first)
            wordlist.remove(it.first)
            true
        } else
            false
    }
    return Pair(bulls, list.count { it in wordlist })
}
