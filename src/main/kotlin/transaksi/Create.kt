package transaksi

import menu.tampilkanSeluruhMenu
import model.Menu
import model.Pesanan
import model.Transaksi
import system.MenuManager
import system.SystemOutput
import system.TransaksiManager
import system.UserInteract
import java.time.LocalDateTime

fun buatTransaksi() {
    val tambahTransaksiSign = """
        ==============================
        Tambah Transaksi Baru
        ==============================
    """.trimIndent()

    println("\n$tambahTransaksiSign")

    val itemDibeli = ArrayList<Pesanan>()
    var totalHarga = 0.0

    do {
        tampilkanSeluruhMenu()
        println("Ketik 0 untuk selesai memilih!")
        if (!itemDibeli.isEmpty()) {
            println("Pesanan yang telah dipilih:")
            for (item in itemDibeli) {
                println("\t- ${item.menu.ambilNama()} x${item.jumlah} - ${item.subtotal}")
            }
        }
        println("Total harga saat ini: $totalHarga")
        val pilihMenuId: Int? = UserInteract.pilihOpsiAngka("Pilih id menu yang ingin Anda beli: ")
        if (pilihMenuId == 0) {
            break
        }
        val menuDipilih: Menu? = MenuManager.findMenuById(pilihMenuId)
        if (pilihMenuId == null || menuDipilih == null) {
            SystemOutput.tampilkanPesanError("Id tidak ada di dalam list menu!")
            continue
        }
        println("Menu yang Anda pilih: ")
        println("${menuDipilih.ambilNama()} - ${menuDipilih.ambilHarga()} (Stok: ${menuDipilih.ambilStok()})")
        val jumlahPesanan:Int? = UserInteract.pilihOpsiAngka("Masukkan jumlah pesanan: ")
        if (jumlahPesanan == null || jumlahPesanan <= 0) {
            SystemOutput.tampilkanPesanError("Jumlah pesanan harus bilangan asli!")
            continue
        }
        if (menuDipilih.ambilStok() - jumlahPesanan < 0) {
            SystemOutput.tampilkanPesanError("Jumlah pesanan melebihi stok pada menu!")
            continue
        }
        val pesananBaru = Pesanan(menuDipilih, jumlahPesanan)
        itemDibeli.add(pesananBaru)
        totalHarga += pesananBaru.subtotal
    } while (pilihMenuId != null && pilihMenuId != 0)

    if (itemDibeli.isNotEmpty()) {
        val tanggalSaatIni = LocalDateTime.now().toString()
        val newTransaksi = Transaksi(TransaksiManager.getNextTransaksiId(), itemDibeli, totalHarga, tanggalSaatIni)

        val totalPesananLabel = """
        ==============================
        Total harga: $totalHarga
        ==============================
    """.trimIndent()

        println("Selesai memilih pesanan.")
        newTransaksi.tampilkanDaftarPesanan()
        println("\n$totalPesananLabel")

        val konfirmasiPilihan = UserInteract.konfirmasiYaTidak("Apakah sudah yakin transaksinya?")
        if (konfirmasiPilihan == false) {
            println("Transaksi dibatalkan.")
            UserInteract.konfirmasiEnter()
            return
        }

        // kurangi jumlah stok pada menu karena transaksi sudah dikonfirmasi
        newTransaksi.daftarPesanan.forEach { pesanan ->
            val menuPadaPesanan: Menu = pesanan.menu
            menuPadaPesanan.kurangiStok(pesanan.jumlah)
            println("Stakstikstok: ${menuPadaPesanan.ambilStok()}")
            MenuManager.editMenu(menuPadaPesanan.ambilId(), menuPadaPesanan)
        }
        MenuManager.saveMenuList()

        TransaksiManager.addTransaksi(newTransaksi)
        println("Transaksi berhasil ditambahkan!")
    } else {
        println("Tidak ada item yang ingin dibeli!")
    }
}