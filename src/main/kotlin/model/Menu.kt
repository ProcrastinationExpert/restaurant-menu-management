package model

import system.SystemOutput

abstract class Menu(private val id: Int, private val tipe:String, private var nama: String, private var harga: Double, private var stok: Int) {
    fun ambilId(): Int {
        return this.id
    }
    fun ambilNama(): String {
        return this.nama
    }
    fun ambilHarga(): Double {
        return this.harga
    }
    fun ambilStok(): Int {
        return this.stok
    }
    fun ambilTipe(): String {
        return this.tipe
    }
    fun kurangiStok(jumlahStok: Int) {
        if (this.stok == 0 || jumlahStok >= this.stok) {
            SystemOutput.tampilkanPesanMiniError("Stok kosong atau jumlah stok yang diambil berlebihan!")
        } else {
            this.stok -= jumlahStok
        }
    }
    abstract fun displayInfo()
}