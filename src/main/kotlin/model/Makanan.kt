package model

class Makanan(id: Int, nama: String, harga: Float, stok: Int, var isPedas: Boolean): Menu(id, nama, harga, stok) {
    fun gantiPedas(isPedas: Boolean) {
        this.isPedas = isPedas
    }
    override fun displayInfo() {
        println("🍗 - [${cekId()}] - $nama \t-\t $harga \t(🌶️${if (isPedas) "✔" else "✖"}) (Tersedia? ${stok>0})")
    }
}