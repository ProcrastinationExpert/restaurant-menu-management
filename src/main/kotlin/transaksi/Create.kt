package transaksi

import model.Menu
import model.Pesanan
import model.Transaksi
import system.MenuManager
import system.SystemOutput
import system.UserInteract
import system.TransaksiManager
import menu.tampilkanSeluruhMenu
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val hari = arrayOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")
val bulan = arrayOf("Januari", "Februari", "Maret", "April", "Mei", "Juni",
    "Juli", "Agustus", "September", "Oktober", "November", "Desember")

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
            SystemOutput.tampilkanPesanMiniError("Id tidak ada di dalam list menu!")
            continue
        }
        println("Menu yang Anda pilih: ")
        println("${menuDipilih.ambilNama()} - ${menuDipilih.ambilHarga()} (Stok: ${menuDipilih.ambilStok()})")
        val jumlahPesanan:Int? = UserInteract.pilihOpsiAngka("Masukkan jumlah pesanan: ")
        if (jumlahPesanan == null || jumlahPesanan <= 0) {
            SystemOutput.tampilkanPesanMiniError("Jumlah pesanan harus bilangan asli!")
            continue
        }
        if (menuDipilih.ambilStok() - jumlahPesanan < 0) {
            SystemOutput.tampilkanPesanMiniError("Jumlah pesanan melebihi stok pada menu!")
            continue
        }
        var menuSudahDipesan = false
        for (item in itemDibeli) {
            if (item.menu.ambilId() == menuDipilih.ambilId()) {
                item.jumlah += jumlahPesanan
                item.subtotal = item.menu.ambilHarga() * item.jumlah
                menuSudahDipesan = true
                break
            }
        }

        if (!menuSudahDipesan) {
            val pesananBaru = Pesanan(menuDipilih, jumlahPesanan)
            itemDibeli.add(pesananBaru)
        }

        totalHarga = 0.0
        for (item in itemDibeli) {
            totalHarga += item.subtotal
        }
    } while (pilihMenuId != null && pilihMenuId != 0)

    if (itemDibeli.isNotEmpty()) {

        val now = LocalDateTime.now()
        val namaHari = hari[now.dayOfWeek.value - 1]
        val tanggal = now.dayOfMonth
        val namaBulan = bulan[now.monthValue - 1]
        val tahun = now.year
        val formatWaktu = DateTimeFormatter.ofPattern("HH:mm:ss")
        val waktuSaatIni = now.format(formatWaktu)
        val tanggalLengkap = "$namaHari, $tanggal $namaBulan $tahun - $waktuSaatIni"

        val newTransaksi = Transaksi(TransaksiManager.getNextTransaksiId(), itemDibeli, totalHarga, tanggalLengkap)

        val totalPesananLabel = """
        ==============================
        Total harga: $totalHarga
        ==============================
    """.trimIndent()
        
        println("Selesai memilih pesanan.")
        newTransaksi.tampilkanDaftarPesanan()
        println(totalPesananLabel)

        val konfirmasiPilihan = UserInteract.konfirmasiYaTidak("Apakah sudah yakin transaksinya?")
        if (konfirmasiPilihan == false) {
            SystemOutput.tampilkanPesanMiniError("Transaksi dibatalkan.")
            UserInteract.konfirmasiEnter()
            return
        }

        newTransaksi.daftarPesanan.forEach { pesanan ->
            val menuPadaPesanan: Menu = pesanan.menu
            menuPadaPesanan.kurangiStok(pesanan.jumlah)
            MenuManager.editMenu(menuPadaPesanan.ambilId(), menuPadaPesanan)
        }
        MenuManager.saveMenuList()

        TransaksiManager.addTransaksi(newTransaksi)
        SystemOutput.tampilkanPesanSukses("Transaksi berhasil dibuat.")
    } else {
        SystemOutput.tampilkanPesanMiniError("Tidak ada item yang ingin dibeli!")
    }
}