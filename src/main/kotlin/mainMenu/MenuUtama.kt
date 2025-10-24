package mainMenu

import system.SystemOutput
import system.UserInteract

fun mainMenu() {
    val menuInfo = """
        ==============================
        Program Manajemen Menu Restoran
        ==============================
        1. Kelola Menu
        2. Kelola Transaksi
        3. Keluar
        ==============================
    """.trimIndent()

    do {
        println("\n$menuInfo")
        val option = UserInteract.pilihOpsiAngka("Pilih opsi: ")
        when (option) {
            1 -> {
                kelolaMenu()
            }
            2 -> {
                kelolaTransaksi()
            }
            3 -> {
                exit()
            }
            else -> SystemOutput.tampilkanPesanMiniError("Opsi tidak tersedia!")
        }
    } while (option !in 1..3)
}