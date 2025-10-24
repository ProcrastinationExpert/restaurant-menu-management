package transaksi

import system.TransaksiManager

fun tampilkanSeluruhTransaksi() {
    val menuSign = """
        ==============================
        Daftar Transaksi Restoran
        ==============================
        """.trimIndent()
    println("\n$menuSign")
    TransaksiManager.showTransaksiList()
    println("==============================")
}