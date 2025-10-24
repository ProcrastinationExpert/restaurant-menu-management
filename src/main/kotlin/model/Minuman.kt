package model

class Minuman(id: Int, tipe:String, nama: String, harga: Double, stok: Int, var isDingin: Boolean): Menu(id, tipe, nama, harga, stok) {
    fun cekDingin(): Boolean {
        return this.isDingin
    }
    override fun displayInfo() {
        println("🍺 - [${ambilId()}] - ${ambilNama()} - ${ambilHarga()} (❄️${if (cekDingin()) "✔" else "✖"}) (Tersedia? ${ambilStok()>0})")
    }
}