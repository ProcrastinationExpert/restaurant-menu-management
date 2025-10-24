package system

import model.Menu

object MenuManager {
    const val MENUJSONFILEPATH:String = "src/main/kotlin/data/menu.json"

    var menuList = arrayListOf<Menu>()

    fun getFirstMenu(): Menu? {
        return if (menuList.isEmpty()) null else menuList.first()
    }

    fun getLastMenu(): Menu? {
        return if (menuList.isEmpty()) null else menuList.last()
    }

    fun getNextMenuId(): Int {
        return getLastMenu()?.ambilId()?.plus(1) ?: 1
    }

    fun updateMenuList() {
        menuList.clear()
        menuList.addAll(DataManager.getMenuData(MENUJSONFILEPATH))
    }

    fun saveMenuList() {
        DataManager.saveMenuData(menuList, MENUJSONFILEPATH)
    }

    fun showMenuList() {
        if (menuList.isEmpty()) {
            println("Tidak ada menu yang terdaftar!")
        }
        for (menu in menuList) {
            menu.displayInfo()
        }
    }

    fun findMenuById(id: Int?): Menu? {
        val idFirst = getFirstMenu()?.ambilId()
        val idLast = getLastMenu()?.ambilId()
        if (id == null || idFirst == null || idLast == null || (id < idFirst) || (id > idLast)) return null
        for (menuElement in menuList) {
            if (menuElement.ambilId() == id) {
                return menuElement
            }
        }
        return null
    }

    fun addMenu(newMenu: Menu) {
        menuList.add(newMenu)
    }

    fun editMenu(id:Int, editedMenu: Menu) {
        val menuToEdit = findMenuById(id)
        if (menuToEdit == null) {
            println("Menu dengan id tersebut tidak dapat ditemukan!")
            return
        }
        menuList[menuList.indexOf(menuToEdit)] = editedMenu
        println("Menu berhasil diedit")
    }

    fun removeMenu(id: Int) {
        val menuToRemove = findMenuById(id)
        if (menuToRemove == null) {
            println("Menu dengan id tersebut tidak dapat ditemukan!")
            return
        }
        menuList.remove(menuToRemove)
        println("Menu berhasil dihapus")
    }
}