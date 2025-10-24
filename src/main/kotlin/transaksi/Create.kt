package transaksi

import model.Menu
import model.Pesanan
import model.Transaksi
import system.MenuManager
import system.SystemOutput
import system.UserInteract
import system.TransaksiManager
import menu.tampilkanSeluruhMenu


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
        MenuManager.updateMenuList()
        tampilkanSeluruhMenu()
        println("Ketik 0 untuk selesai memilih!")
        if (!itemDibeli.isEmpty()) {
            println("Pesanan yang telah dipilih:")
            for (item in itemDibeli) {
                println("\t- ${item.namaMenu} x${item.jumlah} - ${item.subtotal}")
            }
        }
        println("==============================")
        println("Total harga saat ini: $totalHarga")
        println("==============================")
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
        println("==============================")
        val jumlahPesanan:Int? = UserInteract.pilihOpsiAngka("Masukkan jumlah pesanan: ")
        if (jumlahPesanan == null || jumlahPesanan <= 0) {
            SystemOutput.tampilkanPesanMiniError("Jumlah pesanan harus bilangan asli dan tidak boleh null!")
            continue
        }
        if (menuDipilih.ambilStok() - jumlahPesanan < 0) {
            SystemOutput.tampilkanPesanMiniError("Jumlah pesanan melebihi stok pada menu!")
            continue
        }
        var menuSudahDipesan = false
        var lebihDariStok = false
        for (item in itemDibeli) {
            if (item.menuId == menuDipilih.ambilId()) {
                if (item.jumlah + jumlahPesanan > menuDipilih.ambilStok()) {
                    SystemOutput.tampilkanPesanMiniError("Jumlah pesanan melebihi stok pada menu!")
                    lebihDariStok = true
                } else {
                    item.jumlah += jumlahPesanan
                    item.subtotal = item.hargaSatuan * item.jumlah
                    menuSudahDipesan = true
                }
                break
            }
        }
        if (lebihDariStok) continue

        if (!menuSudahDipesan) {
            val pesananBaru = Pesanan(
                menuId = menuDipilih.ambilId(),
                namaMenu = menuDipilih.ambilNama(),
                hargaSatuan =  menuDipilih.ambilHarga(),
                jumlah =  jumlahPesanan
            )
            itemDibeli.add(pesananBaru)
        }

        totalHarga = 0.0
        for (item in itemDibeli) {
            totalHarga += item.subtotal
        }


    } while (pilihMenuId != null && pilihMenuId != 0)

    if (itemDibeli.isNotEmpty()) {
        val newTransaksi = Transaksi(
            daftarPesanan = itemDibeli,
            totalHarga =  totalHarga,
        )

        val totalPesananLabel = """
        ==============================
        Total harga: $totalHarga
        ==============================
    """.trimIndent()
        
        println("Selesai memilih pesanan.")
        println("==============================")
        newTransaksi.tampilkanDaftarPesanan()
        println("==============================")
        println(totalPesananLabel)
        println("==============================")

        val konfirmasiPilihan = UserInteract.konfirmasiYaTidak("Apakah sudah yakin transaksinya?")
        if (konfirmasiPilihan != true) {
            SystemOutput.tampilkanPesanMiniError("Transaksi dibatalkan.")
            return
        }

        newTransaksi.daftarPesanan.forEach { pesanan ->
            val menuAsli: Menu? = MenuManager.findMenuById(pesanan.menuId)
            if (menuAsli != null) {
                menuAsli.kurangiStok(pesanan.jumlah)
                MenuManager.editMenu(menuAsli.ambilId(), menuAsli)
            }
        }
        MenuManager.saveMenuList()

        TransaksiManager.addTransaksi(newTransaksi)
        SystemOutput.tampilkanPesanSukses("Transaksi berhasil dibuat.")
    } else {
        SystemOutput.tampilkanPesanMiniError("Tidak ada item yang ingin dibeli!")
    }
}