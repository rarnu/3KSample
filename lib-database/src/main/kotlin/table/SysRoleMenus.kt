package com.isyscore.wh.database.table

import com.isyscore.wh.database.entity.SysRoleMenu
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.long

object SysRoleMenus: Table<SysRoleMenu>("sys_role_menu") {
    var roleId = long("role_id").bindTo { it.roleId }
    var menuId = long("menu_id").bindTo { it.menuId }
}

val Database.sysRoleMenus get() = this.sequenceOf(SysRoleMenus)