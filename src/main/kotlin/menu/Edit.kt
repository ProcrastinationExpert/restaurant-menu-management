package menu

import model.Makanan
import model.Menu
import model.Minuman
import system.MenuManager
import system.SystemOutput

fun editMenuDalamList() {
    println("==============================")
    print("Masukkan id menu yang ingin diedit (nomor yang terdapat dalam []): ")
    val idEdit = readlnOrNull()?.toIntOrNull() ?: -1
    val menuSebelumDiedit = MenuManager.findMenuById(idEdit)
    if (menuSebelumDiedit == null) {
        SystemOutput.tampilkanPesanMiniError("Menu dengan id tersebut tidak ada")
        return
    }

    println("Menu yang akan Anda edit:")
    menuSebelumDiedit.displayInfo()

    print("Masukkan nama menu baru (kosongkan jika tidak perlu)\t: ")
    val inputNama = readlnOrNull()

    val namaBaru = if (inputNama.isNullOrBlank()) menuSebelumDiedit.ambilNama()
    else inputNama

    print("Masukkan harga menu baru (kosongkan jika tidak perlu)\t: ")
    val inputHarga = readlnOrNull()

    val hargaBaru = if (inputHarga.isNullOrBlank()) menuSebelumDiedit.ambilHarga()
    else inputHarga.toDoubleOrNull() ?: menuSebelumDiedit.ambilHarga()

    if (hargaBaru < 0) {
        SystemOutput.tampilkanPesanMiniError("Nominal harga tidak boleh kurang dari nol!")
        return
    }

    print("Masukkan stok menu baru (kosongkan jika tidak perlu)\t: ")
    val inputStok: String? = readlnOrNull()

    val stokBaru = if (inputStok.isNullOrBlank()) menuSebelumDiedit.ambilStok()
    else inputStok.toIntOrNull() ?: menuSebelumDiedit.ambilStok()

    if (stokBaru < 0) {
        SystemOutput.tampilkanPesanMiniError("Jumlah stok tidak boleh kurang dari nol!")
        return
    }

    val menuSetelahEdit: Menu
    when (menuSebelumDiedit) {
        is Makanan -> {
            print("Apakah makanan ini pedas? (kosongkan jika tidak perlu) (ya/tidak)\t: ")
            val inputPedas = readlnOrNull()
            val pedasBaru =
                if (inputPedas.isNullOrBlank()) menuSebelumDiedit.cekPedas()
                else if (inputPedas == "ya") true
                else false
            menuSetelahEdit = Makanan(
                id = menuSebelumDiedit.ambilId(),
                tipe = menuSebelumDiedit.ambilTipe(),
                nama = namaBaru,
                harga = hargaBaru,
                stok = stokBaru,
                isPedas = pedasBaru
            )
        }
        is Minuman -> {
            print("Apakah minuman ini dingin?  (kosongkan jika tidak perlu) (ya/tidak)\t: ")
            val inputDingin = readlnOrNull()
            val dinginBaru =
                if (inputDingin.isNullOrBlank()) menuSebelumDiedit.cekDingin()
                else if (inputDingin == "ya") true
                else false
            menuSetelahEdit = Minuman(
                id = menuSebelumDiedit.ambilId(),
                tipe = menuSebelumDiedit.ambilTipe(),
                nama = namaBaru,
                harga = hargaBaru,
                stok = stokBaru,
                isDingin = dinginBaru
            )
        }
        else -> {
            SystemOutput.tampilkanPesanMiniError("Terdapat menu yang bertipe bukan makanan dan minuman (anomali)")
            return
        }
    }
    MenuManager.editMenu(idEdit, menuSetelahEdit)
}