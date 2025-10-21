package org.example

class Angka(val angka: Int) {
    fun Int.tambah10(): Int {
        return this + 10
    }

    var baca: String
        get() = "Angka ini dibaca"
        set(angkas) {
            println("abcdefhg $angkas")
        }
}

open class Kendaraan(val merek: String) {
    open fun info() {
       println("Ini adalah kendaraan dengan merek $merek")
    }
}

class Mobil(merek: String, val kecepatan: Int): Kendaraan(merek) {
    override fun info() {
        println("Ini adalah mobil dengan merek $merek yang memiliki kecepatan sebesar $kecepatan km/jam")
    }
    fun jalan() {
        println("Mobil dengan merek $merek sedang berjalan")
    }
}

val Int.isEven: Boolean
    get() = this % 2 == 0

fun main() {
    val menuList = """
        ========================================
        Program manajemen menu restoran
        ========================================
    """.trimIndent()

    println(menuList)

    val mobilSaya = Mobil("Avanza", 999)
    mobilSaya.info()
    mobilSaya.jalan()

    val kendaraanSiapa = Kendaraan("Esemka")
    kendaraanSiapa.info()

    val angkaSaya = Angka(999)
    println(angkaSaya.baca)
    angkaSaya.baca = "1230942908"

    val angkarandom = 1999
    println("Apakah angka random tersebut genap? ${angkarandom.isEven}")

    fun String?.getLength(): Int {
        return this?.length ?: 0
    }

    var abcde: String? = "asasaas"
    abcde = null
    println(abcde.getLength())



}