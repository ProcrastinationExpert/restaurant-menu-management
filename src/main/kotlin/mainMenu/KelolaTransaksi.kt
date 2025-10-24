package mainMenu

import system.UserInteract
import system.SystemOutput
import system.TransaksiManager
import transaksi.*

fun kelolaTransaksi() {
    TransaksiManager.updateDaftarTransaksi()

    val menuInfo = """
        ==============================
        Kelola Transaksi Restoran
        ==============================
        1. Lihat Daftar Transaksi
        2. Tambahkan Transaksi Baru (untuk pesan makanan)
        3. Hapus Transaksi
        4. Simpan dan Kembali ke Menu Utama
        ==============================
    """.trimIndent()

    do {
        println("\n$menuInfo")
        val pilih = UserInteract.pilihOpsiAngka("Pilih opsi: ")
        when (pilih) {
            1 -> {
                tampilkanSeluruhTransaksi()
                UserInteract.konfirmasiEnter()
            }
            2 -> {
                buatTransaksi()
                UserInteract.konfirmasiEnter()
            }
            3 -> {
                hapusTransaksi()
                UserInteract.konfirmasiEnter()
            }
            4 -> {
                TransaksiManager.saveDaftarTransaksi()
            }
            else -> {
                SystemOutput.tampilkanPesanError("Pilihan tidak tersedia!")
            }
        }
    } while (pilih != 4)
    mainMenu()
}