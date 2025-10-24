import mainMenu.mainMenu
import system.SystemOutput

fun main() {
    try {
        mainMenu()
    } catch (error: Exception) {
        SystemOutput.tampilkanPesanFatalError("$error")
        error.printStackTrace()
    }
}