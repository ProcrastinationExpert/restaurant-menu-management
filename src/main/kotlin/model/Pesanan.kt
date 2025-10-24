package model

data class Pesanan(
    val menu: Menu,
    val jumlah: Int,
    val subtotal: Double = menu.ambilHarga() * jumlah
) {
    fun displayInfo() {
        println("${menu.ambilNama()} x$jumlah (${subtotal})")
    }
}