package mainMenu

import system.SystemOutput
import system.UserInteract

fun exit() {
    do {
        val konfirmasi = UserInteract.konfirmasiYaTidak("Yakin untuk keluar?")
        when (konfirmasi) {
            true -> {
                println("==============================")
                println("Baiklah, selamat tinggal!")
                println("==============================")
            }
            false -> mainMenu()
            else -> SystemOutput.tampilkanPesanError("Opsi Tidak Tersedia!")
        }
    } while (konfirmasi == null)
}