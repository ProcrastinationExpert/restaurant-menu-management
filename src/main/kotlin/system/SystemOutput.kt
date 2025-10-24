package system

import system.Colors.RED_COLOR
import system.Colors.RESET_COLOR
import system.Colors.YELLOW_COLOR
import system.Colors.GREEN_COLOR

object SystemOutput {
    fun tampilkanPesanSukses(pesan:String) {
        val tampilanSukses:String = """
            ==============✅==============
            $GREEN_COLOR$pesan$RESET_COLOR
            ==============✅==============
        """.trimIndent()
        println("\n$tampilanSukses")
    }
    fun tampilkanPesanWarning(pesan:String) {
        val tampilanWarning:String = """
            ==============⚠️==============
            $YELLOW_COLOR$pesan$RESET_COLOR
            ==============⚠️==============
        """.trimIndent()
        println("\n$tampilanWarning")
    }
    fun tampilkanPesanError(pesan:String) {
        val tampilanError:String = """
            ==============❌==============
            $RED_COLOR$pesan$RESET_COLOR
            ==============❌==============
        """.trimIndent()
        println("\n$tampilanError")
    }
}