package mainMenu

import java.util.Scanner

fun menu() {
    val scanner = Scanner(System.`in`)

    val menuInfo = """
        Program Manajemen Menu Restoran
        ==============================
        1. Kelola Menu
        2. Kelola Transaksi
        3. Keluar
        ==============================
    """.trimIndent()

    do {
        println("\n$menuInfo")
        print("Pilih opsi: ")
        val option = scanner.nextInt()
        if (option !in 1..3) {
            println("==============!!==============")
        }

        when (option) {
            1 -> {
                kelolaMenu()
            }
            2 -> {
                println("Di sini menu untuk mengelola transaksi")
            }
            3 -> {
                exit()
            }
            else -> println("\u001B[31mOpsi tidak tersedia!\u001B[0m")
        }

        if (option !in 1..3) {
            println("==============!!==============")
        }
    } while (option !in 1..3)
}