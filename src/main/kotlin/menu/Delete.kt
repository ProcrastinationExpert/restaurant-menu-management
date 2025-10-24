package menu

import system.MenuManager
import system.UserInteract

fun hapusMenuDalamList() {
    tampilkanSeluruhMenu()
    val idHapus = UserInteract.pilihOpsiAngka("Masukkan id menu yang ingin dihapus (nomor yang terdapat dalam []): ")
    MenuManager.removeMenu(idHapus)
}