package menu

import system.MenuManager
import java.util.Scanner

fun hapusMenuDalamList() {
    val scanner = Scanner(System.`in`)
    tampilkanSeluruhMenu()
    print("Masukkan id menu yang ingin dihapus (nomor yang terdapat dalam []): ")
    val idHapus = scanner.nextInt()
    MenuManager.removeMenu(idHapus)
}