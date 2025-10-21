package model

class Minuman(id: Int, nama: String, harga: Float, stok: Int, var isDingin: Boolean): Menu(id, nama, harga, stok) {
    fun gantiDingin(isDingin: Boolean) {
        this.isDingin = isDingin
    }
    override fun displayInfo() {
        println("🍷 - [${cekId()}] - $nama \t-\t $harga \t(❄️${if (isDingin) "✔" else "✖"}) (Tersedia? ${stok>0})")
    }
}