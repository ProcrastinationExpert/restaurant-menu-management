package menu

import system.MenuManager

fun tampilkanSeluruhMenu() {
    val menuSign = """
        ==============================
        Daftar Menu Restoran
        ==============================
        """.trimIndent()
    println("\n$menuSign")
    MenuManager.showMenuList()
    println("==============================")
}