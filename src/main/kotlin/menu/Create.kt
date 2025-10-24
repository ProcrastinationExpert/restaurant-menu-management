package menu

import model.Makanan
import model.Menu
import model.Minuman
import system.MenuManager
import system.SystemOutput
import java.util.Scanner

fun tambahMenuBaru() {
    val scanner = Scanner(System.`in`)
    val tambahMenuSign = """
                    ==============================
                    Tambah Menu Restoran
                    ==============================
                """.trimIndent()
    println("\n$tambahMenuSign")
    print("Tipe menu (makanan/minuman): ")
    val tipeMenu = readln().lowercase()
    print("Nama menu: ")
    val namaMenu = readln()
    print("Harga per porsi: ")
    val hargaPerPorsi = scanner.nextDouble()
    print("Stok awal: ")
    val stok = scanner.nextInt()
    scanner.nextLine()

    var pesanKonfirmasi: String?
    var menuUntukDitambah: Menu?
    when (tipeMenu) {
        "makanan" -> {
            print("Apakah makanannya pedas? (ya/tidak): ")
            val cekPedas = scanner.nextLine().lowercase()
            val isPedas: Boolean = cekPedas == "ya"
            pesanKonfirmasi = """
                        _________________________________________
                        | Konfirmasi tambahan menu baru         
                        | _______________________________________
                        | Tipe  : $tipeMenu
                        | Nama  : $namaMenu
                        | Harga : $hargaPerPorsi
                        | Stok  : $stok
                        | Pedas : $isPedas
                        _________________________________________
                        """.trimIndent()
            menuUntukDitambah = Makanan(id = MenuManager.getNextMenuId(),
                tipe = tipeMenu,
                nama = namaMenu,
                harga = hargaPerPorsi,
                stok = stok,
                isPedas = isPedas
            )
        }
        "minuman" -> {
            print("Apakah minumannya dingin? (ya/tidak): ")
            val cekDingin = scanner.nextLine().lowercase()
            val isDingin: Boolean = cekDingin == "ya"
            pesanKonfirmasi = """
                        _________________________________________
                        | Konfirmasi tambahan menu baru         
                        | _______________________________________
                        | Tipe  : $tipeMenu
                        | Nama  : $namaMenu
                        | Harga : $hargaPerPorsi
                        | Stok  : $stok
                        | Dingin: $isDingin
                        _________________________________________
                        """.trimIndent()
            menuUntukDitambah = Minuman(id = MenuManager.getNextMenuId(),
                tipe = tipeMenu,
                nama = namaMenu,
                harga = hargaPerPorsi,
                stok = stok,
                isDingin = isDingin
            )
        }
        else -> {
            SystemOutput.tampilkanPesanMiniError("Tipe menu tidak diketahui!")
            return
        }
    }
    println("\n$pesanKonfirmasi")
    print("Apakah menu yang ingin ditambahkan sudah sesuai? (ya/tidak): ")
    val konfirmasi = readln().lowercase()

    println("==============================")
    if (konfirmasi != "ya") {
        SystemOutput.tampilkanPesanMiniError("Menu batal ditambahkan!")
    } else {
        MenuManager.addMenu(menuUntukDitambah)
        SystemOutput.tampilkanPesanSukses("Menu berhasil ditambahkan!")
    }
}