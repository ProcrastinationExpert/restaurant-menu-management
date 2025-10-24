package model

class Makanan(id: Int, tipe:String, nama: String, harga: Double, stok: Int, var isPedas: Boolean): Menu(id, tipe, nama, harga, stok) {
    fun cekPedas(): Boolean {
        return this.isPedas
    }
     override fun displayInfo() {
        println("🍗 - [${ambilId()}] - ${ambilNama()} - ${ambilHarga()} (🌶️${if (cekPedas()) "✔" else "✖"}) (Tersedia? ${ambilStok()>0})")
    }
}