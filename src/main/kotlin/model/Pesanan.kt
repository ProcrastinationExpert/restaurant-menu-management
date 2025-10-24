package model

data class Pesanan(
    val menu: Menu,
    var jumlah: Int,
    var subtotal: Double = menu.ambilHarga() * jumlah
) {
    fun displayInfo() {
        println("${menu.ambilNama()} x$jumlah ($subtotal")
    }
}
