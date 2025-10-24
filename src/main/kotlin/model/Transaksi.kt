package model

data class Transaksi(
    val id: Int,
    val daftarPesanan: ArrayList<Pesanan>,
    val totalHarga: Double,
    val tanggal: String
) {
    fun tampilkanDaftarPesanan() {
        println("==============================")
        println("Daftar pesanan: ")
        for (p in daftarPesanan) {
            p.displayInfo()
        }
        println("==============================")
    }
    fun tampilkanInfo() {
        println("[$id]")
        println("Daftar pesanan(${daftarPesanan.size}):")
        for (p in daftarPesanan) {
            print("\t- ")
            p.displayInfo()
        }
        println("Total Harga: $totalHarga")
        println("Tanggal: $tanggal")
    }
}