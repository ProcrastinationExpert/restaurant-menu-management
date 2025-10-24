package mainMenu

import menu.*
import system.MenuManager
import system.SystemOutput
import system.UserInteract

fun kelolaMenu() {
    MenuManager.updateMenuList()

    val menuInfo = """
        ==============================
        Kelola Menu Restoran
        ==============================
        1. Lihat daftar menu
        2. Tambahkan menu baru
        3. Edit menu
        4. Hapus menu
        5. Simpan menu dan kembali ke menu awal
        ==============================
    """.trimIndent()

    do {
        println("\n$menuInfo")
        val pilih = UserInteract.pilihOpsiAngka("Pilih opsi: ")

        when (pilih) {
            1 -> {
                tampilkanSeluruhMenu()
                UserInteract.konfirmasiEnter()
            }
            2 -> {
                tambahMenuBaru()
                UserInteract.konfirmasiEnter()
            }
            3 -> {
                tampilkanSeluruhMenu()
                editMenuDalamList()
                UserInteract.konfirmasiEnter()
            }
            4 -> {
                hapusMenuDalamList()
                UserInteract.konfirmasiEnter()
            }
            5 -> {
                MenuManager.saveMenuList()
            }
            else -> SystemOutput.tampilkanPesanError("Opsi tidak tersedia!")
        }
    } while (pilih != 5)
    mainMenu()
}