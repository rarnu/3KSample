package com.isyscore.wh.database.entity

import org.ktorm.entity.Entity

interface SysRoleMenu: Entity<SysRoleMenu> {
    companion object : Entity.Factory<SysRoleMenu>()
    var roleId: Long
    var menuId: Long
}