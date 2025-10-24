package system

import model.Transaksi

object TransaksiManager {
    private const val TRANSAKSIJSONFILEPATH:String = "src/main/kotlin/data/transaksi.json"
    private var daftarTransaksi = arrayListOf<Transaksi>()

    fun updateDaftarTransaksi() {
        daftarTransaksi.clear()
        daftarTransaksi.addAll(DataManager.getTransaksiData(TRANSAKSIJSONFILEPATH))
    }

    fun saveDaftarTransaksi() {
        DataManager.saveTransaksiData(daftarTransaksi, TRANSAKSIJSONFILEPATH)
    }

    fun getNextTransaksiId(): Int {
        return if (daftarTransaksi.isEmpty()) 1 else daftarTransaksi.last().id + 1
    }

    fun addTransaksi(transaksi: Transaksi) {
        daftarTransaksi.add(transaksi)
    }

    fun findTransaksiById(id: Int): Transaksi? {
        return daftarTransaksi.find { it.id == id }
    }

    fun showTransaksiList() {
        if (daftarTransaksi.isEmpty()) {
            SystemOutput.tampilkanPesanMiniError("Tidak ada transaksi yang pernah dilakukan!")
            return
        }
        for (transaksi in daftarTransaksi) {
            transaksi.tampilkanInfo()
            println("------------------------------")
        }
    }

    fun removeTransaksi(id: Int) {
        val transaksiToRemove = findTransaksiById(id)
        if (transaksiToRemove == null) {
            SystemOutput.tampilkanPesanMiniError("Transaksi dengan id tersebut tidak dapat ditemukan")
            return
        }
        daftarTransaksi.remove(transaksiToRemove)
        println("Transaksi berhasil dihapus")
    }
}