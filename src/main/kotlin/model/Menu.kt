package model

open class Menu(private val id: Int, var nama: String, var harga: Float, var stok: Int) {
    fun gantiNama(namaBaru: String) {
        this.nama = namaBaru
    }
    fun gantiHarga(hargaBaru: Float) {
        this.harga = hargaBaru
    }
    fun cekId(): Int {
        return this.id
    }
    fun tambahStok(jumlahStok: Int) {
        this.stok += jumlahStok
    }
    fun kurangiStok(jumlahStok: Int) {
        if (this.stok == 0 || jumlahStok >= this.stok) {
            error("Stok kosong atau parameter berlebihan!")
        } else {
            this.stok -= jumlahStok
        }
    }
    open fun displayInfo() {
        println("❓ - [${cekId()}] - $nama \t-\t $harga (Tersedia? ${stok>0})")
    }
}