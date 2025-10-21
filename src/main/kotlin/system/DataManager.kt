package system

import model.Menu
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class DataManager(val filePathMenu:String) {
    private val gson = Gson()

    fun saveMenuData(arrayList: ArrayList<Menu>) {
        val json = gson.toJson(arrayList)
        File(filePathMenu).writeText(json)
        println("Berhasil melakukan konversi array ke $filePathMenu")
    }

    fun getMenuData(): ArrayList<Menu> {
        val file = File(filePathMenu)
        if (!file.exists()) {
            println("$filePathMenu tidak ditemukan")
            return arrayListOf()
        }
        val json = file.readText()
        if (json.isBlank()) {
            println("$filePathMenu masih kosong!")
            return arrayListOf()
        }
        val listType = object : TypeToken<ArrayList<Menu>>() {}.type
        return gson.fromJson(json, listType)
    }
}