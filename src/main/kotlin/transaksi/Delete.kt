package transaksi

import system.SystemOutput
import system.TransaksiManager
import system.UserInteract

fun hapusTransaksi() {
    tampilkanSeluruhTransaksi()
    println("==============================")
    val idToDelete = UserInteract.pilihOpsiAngka("Masukkan id transaksi yang ingin dihapus: ")
    if (idToDelete == null) {
        SystemOutput.tampilkanPesanError("Id tidak boleh kosong!")
        return
    }
    TransaksiManager.removeTransaksi(idToDelete)
}