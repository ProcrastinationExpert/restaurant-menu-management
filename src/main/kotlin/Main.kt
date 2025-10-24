import mainMenu.mainMenu

fun main() {
    try {
        mainMenu()
    } catch (error: NullPointerException) {
        println("==============!!==============")
        println("Program error: $error")
        error.printStackTrace()
    }
}