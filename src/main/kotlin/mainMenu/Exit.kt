package mainMenu

import java.util.Scanner

fun exit() {
    val scanner = Scanner(System.`in`)

    // disini harusnya ada menu untuk menyimpan perubahan json

    val menuKonfirmasiSimpan = """
        ==============================
        Sebelum keluar, apakah Anda ingin menyimpan data menu?
        ==============================
        1. Ya
        2. Tidak
    """.trimIndent()

    do {
        println("\n$menuKonfirmasiSimpan")
        print("Pilih opsi: ")
        val option = scanner.nextInt()

        if (option !in 1..2) {
            println("==============!!==============")
        }

        when (option) {
            1 -> {
                println("==============================")
                println("Data telah berhasil disimpan! (misalnya)")
                println("==============================")
            }

            2 -> {
                println("==============================")
                println("Data menu batal disimpan!")
                println("==============================")
            }
            else -> println("\u001B[31mOpsi tidak tersedia!\u001B[0m")
        }

        if (option !in 1..2) {
            println("==============!!==============")
        }
    } while (option !in 1..2)


    val menuKonfirmasi = """
        ==============================
        Yakin untuk keluar?
        ==============================
        1. Ya
        2. Tidak
    """.trimIndent()

    do {
        println("\n$menuKonfirmasi")
        print("Pilih opsi: ")
        val option = scanner.nextInt()

        if (option !in 1..2) {
            println("==============!!==============")
        }

        when (option) {
            1 -> {
                println("==============================")
                println("Baiklah, selamat tinggal!")
                println("==============================")
            }

            2 -> menu()
            else -> println("\u001B[31mOpsi tidak tersedia!\u001B[0m")
        }

        if (option !in 1..2) {
            println("==============!!==============")
        }
    } while (option !in 1..2)
}