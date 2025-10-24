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
        println("Berhasil menyimpan data array ke $filePathMenu")
    }

    fun getMenuData(filePathMenu:String): ArrayList<Menu> {
        val file = File(filePathMenu)
        if (!file.exists()) { // kalau file json tidak ditemukan makan akan muncul error
            SystemOutput.tampilkanPesanError("$filePathMenu tidak ditemukan")
            return arrayListOf()
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
        println("Berhasil menyimpan file transaksi ke $filePathTransaksi")
    }

    fun getTransaksiData(filePathTransaksi: String): ArrayList<Transaksi> {
        val file = File(filePathTransaksi)
        if (!file.exists()) {
            error("$filePathTransaksi tidak ditemukan")
        }
        val jsonText = file.readText()
        if (jsonText.isBlank()) {
            return arrayListOf()
        }

        val transaksiList = ArrayList<Transaksi>()
        val jsonArray = JsonParser.parseString(jsonText).asJsonArray

        for (element in jsonArray) { // Loop untuk setiap objek Transaksi dalam JSON array
            val objInJsonData = element.asJsonObject
            val daftarPesananJsonArray = objInJsonData["daftarPesanan"].asJsonArray
            val daftarPesanan = ArrayList<Pesanan>()

            // Loop untuk setiap pesanan dalam satu transaksi
            for (pesananElement in daftarPesananJsonArray) {
                val pesananObj = pesananElement.asJsonObject
                val menuObj = pesananObj["menu"].asJsonObject
                val tipe = menuObj["tipe"].asString

                val menu = when (tipe) {
                    "makanan" -> gson.fromJson(menuObj, Makanan::class.java)
                    "minuman" -> gson.fromJson(menuObj, Minuman::class.java)
                    else -> null
                }

                if (menu != null) {
                    val jumlah = pesananObj["jumlah"].asInt
                    val subtotal = pesananObj["subtotal"].asDouble
                    daftarPesanan.add(
                        Pesanan(
                            menu = menu,
                            jumlah = jumlah,
                            subtotal = subtotal
                        )
                    )
                }
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