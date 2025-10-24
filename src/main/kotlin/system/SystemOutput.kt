package system

import system.Colors.RED_COLOR
import system.Colors.RESET_COLOR
import system.Colors.YELLOW_COLOR
import system.Colors.GREEN_COLOR

object SystemOutput {
    fun tampilkanPesanSukses(pesan:String) {
        val tampilanSukses:String = """
            ✅ $GREEN_COLOR$pesan$RESET_COLOR
        """.trimIndent()
        println("\n$tampilanSukses")
    }
    fun tampilkanPesanMiniError(pesan:String) {
        val tampilanWarning:String = """
            ⚠️ $YELLOW_COLOR$pesan$RESET_COLOR
        """.trimIndent()
        println("\n$tampilanWarning")
    }
    fun tampilkanPesanFatalError(pesan:String) {
        val tampilanError:String = """
            ===========❌ERROR❌==========
            $RED_COLOR$pesan$RESET_COLOR
            ===========❌ERROR❌==========
        """.trimIndent()
        println("\n$tampilanError")
    }
}