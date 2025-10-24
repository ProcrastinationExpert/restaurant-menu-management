package system

object UserInteract {
    fun pilihOpsiAngka(labelPilih:String): Int? {
        print("$labelPilih: ")
        val input = readlnOrNull()
        return if (input.isNullOrBlank()) null else input.toIntOrNull()
    }

    fun konfirmasiEnter() {
        print("Tekan ENTER untuk melanjutkan...")
        readln()
    }

    fun konfirmasiYaTidak(pertanyaan:String):Boolean? {
        val menuKonfirmasi = """
        ==============================
        $pertanyaan
        ==============================
        1. Ya
        2. Tidak
        ==============================
    """.trimIndent()
        println("\n$menuKonfirmasi")
        val konfirmasi = pilihOpsiAngka("Pilih opsi: ")
        return when (konfirmasi) {
            1 -> true
            2 -> false
            else -> null
        }
    }


}