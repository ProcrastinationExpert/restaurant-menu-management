package model

import system.TransaksiManager
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val hari = arrayOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")
val bulan = arrayOf("Januari", "Februari", "Maret", "April", "Mei", "Juni",
    "Juli", "Agustus", "September", "Oktober", "November", "Desember")

fun getTanggalSekarang(): String {
    val now = LocalDateTime.now()
    val namaHari = hari[now.dayOfWeek.value - 1]
    val tanggal = now.dayOfMonth
    val namaBulan = bulan[now.monthValue - 1]
    val tahun = now.year
    val formatWaktu = DateTimeFormatter.ofPattern("HH:mm:ss")
    val waktuSaatIni = now.format(formatWaktu)
    val tanggalLengkap = "$namaHari, $tanggal $namaBulan $tahun - $waktuSaatIni"
    return tanggalLengkap
}


data class Transaksi(
    val id: Int = TransaksiManager.getNextTransaksiId(),
    val daftarPesanan: ArrayList<Pesanan>,
    val totalHarga: Double,
    val tanggal: String = getTanggalSekarang()
) {
    fun tampilkanDaftarPesanan() {
        println("Daftar pesanan: ")
        for (p in daftarPesanan) {
            print("\t- ")
            p.displayInfo()
        }
    }
    fun tampilkanInfo() {
        println("[$id]")
        tampilkanDaftarPesanan()
        println("Total Harga: $totalHarga")
        println("Tanggal: $tanggal")
    }
}