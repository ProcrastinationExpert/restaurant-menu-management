import mainMenu.menu

fun main() {
    try {
        menu()
    } catch (error: NullPointerException) {
        println("==============!!==============")
        println("Program error: $error")
        error.printStackTrace()
    }
}