package system

import model.Menu
import com.google.gson.Gson
import com.google.gson.JsonParser
import model.Makanan
import model.Minuman
import model.Pesanan
import model.Transaksi
import java.io.File

object DataManager {
    private val gson = Gson().newBuilder().setPrettyPrinting().create()

    /*
    * BAGIAN MENU
    */

    fun saveMenuData(daftarMenu: ArrayList<Menu>, filePathMenu:String) {
        val jsonText = gson.toJson(daftarMenu) // konversi array ke json dalam bentuk string
        File(filePathMenu).writeText(jsonText) // write text ke file json sesuai dengan pathnya
        SystemOutput.tampilkanPesanSukses("Menu berhasil disimpan ke dalam file json")
    }

    fun getMenuData(filePathMenu:String): ArrayList<Menu> {
        val file = File(filePathMenu)
        if (!file.exists()) { // kalau file json tidak ditemukan makan akan muncul error
            error("$filePathMenu tidak dapat ditemukan")
        }
        val jsonText = file.readText()
        if (jsonText.isBlank()) { // kalo json isinya kosong maka akan mengembalikan list menu yang kosong
            return arrayListOf()
        }
        val jsonArray = JsonParser.parseString(jsonText).asJsonArray
        val menuList = ArrayList<Menu>()
        for (element in jsonArray) {
            val obj = element.asJsonObject
            val tipe = obj["tipe"].asString
            val menu = when (tipe) {
                "makanan" -> gson.fromJson(obj, Makanan::class.java)
                "minuman" -> gson.fromJson(obj, Minuman::class.java)
                else -> null
            }
            if (menu != null) menuList.add(menu)
        }
        return menuList
    }

    /*
    * BAGIAN TRANSAKSI
    */

    fun saveTransaksiData(daftarTransaksi: ArrayList<Transaksi>, filePathTransaksi: String) {
        val jsonData = gson.toJson(daftarTransaksi)
        File(filePathTransaksi).writeText(jsonData)
        SystemOutput.tampilkanPesanSukses("Transaksi berhasil disimpan ke dalam file json")
    }

    fun getTransaksiData(filePathTransaksi: String): ArrayList<Transaksi> {
        val file = File(filePathTransaksi)
        if (!file.exists()) {
            error("$filePathTransaksi tidak dapat ditemukan")
        }
        val jsonText = file.readText()
        if (jsonText.isBlank()) {
            return arrayListOf()
        }

        val transaksiList = ArrayList<Transaksi>()
        val jsonArray = JsonParser.parseString(jsonText).asJsonArray

        for (element in jsonArray) {
            val objInJsonData = element.asJsonObject
            val daftarPesananJsonArray = objInJsonData["daftarPesanan"].asJsonArray
            val daftarPesanan = ArrayList<Pesanan>()

            for (pesananElement in daftarPesananJsonArray) {
                val pesananObj = pesananElement.asJsonObject

                val menuId = pesananObj["menuId"].asInt
                val namaMenu = pesananObj["namaMenu"].asString
                val hargaSatuan = pesananObj["hargaSatuan"].asDouble
                val jumlah = pesananObj["jumlah"].asInt

                val pesanan = Pesanan(
                    menuId = menuId,
                    namaMenu = namaMenu,
                    hargaSatuan = hargaSatuan,
                    jumlah = jumlah
                )
                daftarPesanan.add(pesanan)
            }

            val transaksi = Transaksi(
                id = objInJsonData["id"].asInt,
                daftarPesanan = daftarPesanan,
                totalHarga = objInJsonData["totalHarga"].asDouble,
                tanggal = objInJsonData["tanggal"].asString
            )
            transaksiList.add(transaksi)
        }
        return transaksiList
    }
}