package mainMenu

import model.Makanan
import model.Menu
import model.Minuman
import system.DataManager
import java.util.Scanner

// list menu yang bertipe arrayList
var menuList = arrayListOf<Menu>()

/*
load class data manager yang merujuk pada file menu.json
*/
val dataManager = DataManager("src/main/kotlin/data/menu.json")

fun updateMenuList() {
    menuList.clear()
    menuList.addAll(dataManager.getMenuData())
}

fun saveMenuList() {
    dataManager.saveMenuData(menuList)
}

fun kelolaMenu() {
    val scanner = Scanner(System.`in`)
    updateMenuList()

    val menu = """
        ==============================
        Kelola Menu Restoran
        ==============================
        1. Lihat daftar menu
        2. Tambahkan menu baru
        3. Edit menu
        4. Hapus menu
        5. Kembali ke menu awal
        ==============================
    """.trimIndent()

    do {
        println("\n$menu")
        print("Pilih opsi: ")
        val pilih = scanner.nextInt()

        when (pilih) {
            1 -> {
                println("""
                    ==============================
                    Daftar menu Restoran
                    ==============================
                """.trimIndent())
                if (menuList.size == 0) {
                    println("Tidak ada menu yang terdaftar!")
                } else {
                    for (menu in menuList) {
                        menu.displayInfo()
                    }
                    println("==============================")
                }
                println("Tekan ENTER untuk lanjut")
                readln()
            }
            2 -> {
                println("""
                    ==============================
                    Tambah menu Restoran
                    ==============================
                """.trimIndent())
                print("Tipe menu (Makanan/Minuman): ")
                val tipeMenu = readln().lowercase()
                print("Nama menu: ")
                val namaMenu = readln()
                print("Harga per porsi: ")
                val hargaPerPorsi = scanner.nextFloat()
                print("Stok awal: ")
                val stok = scanner.nextInt()
                scanner.nextLine()

                var pesanKonfirmasi:String? = null
                var menuUntukDitambah:Menu
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
                        menuUntukDitambah = Makanan(menuList.size, namaMenu, hargaPerPorsi, stok, isPedas)
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
                        menuUntukDitambah = Minuman(menuList.size, namaMenu, hargaPerPorsi, stok, isDingin)
                    }
                    else -> {
                        pesanKonfirmasi = """
                        _________________________________________
                        | Konfirmasi tambahan menu baru         
                        | _______________________________________
                        | Tipe  : Tidak diketahui
                        | Nama  : $namaMenu
                        | Harga : $hargaPerPorsi
                        | Stok  : $stok
                        _________________________________________
                        """.trimIndent()
                        menuUntukDitambah = Menu(menuList.size, namaMenu, hargaPerPorsi, stok)
                    }
                }
                println("\n$pesanKonfirmasi")
                print("Apakah menu yang ingin ditambahkan sudah sesuai? (ya/tidak): ")
                val konfirmasi = readln().lowercase()

                println("==============================")
                if (konfirmasi != "ya") {
                    println("Menu batal ditambahkan!")
                } else {
                    menuList.add(menuUntukDitambah)
                    println("Menu berhasil ditambah!")
                }
                println("==============================")
                println("Tekan ENTER untuk melanjutkan")
                println("==============================")
                readln()
            }
            3 -> {
                println("Edit menu")
            }
            4 -> {
                println("Hapus menu")
            }
            5 -> {
                saveMenuList()
                println("Kembali ke menu...")
            }
            else -> println("\u001B[31mOpsi tidak tersedia!\u001B[0m")
        }
    } while (pilih != 5)
    menu()
}