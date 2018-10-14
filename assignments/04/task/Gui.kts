

class Gui{
    companion object {
        fun greeting() {
            println("Welcome to Bulls and Cows game")
        }
        fun restart(): Boolean {
            println("Do you want to play new game (Y/n");
            var ans = readLine()?.tolowerCase ?: ""
             return when {
                ans == "y" -> true
                 ans == "n" -> false
                 else -> restart()
            }
        }
    }
}