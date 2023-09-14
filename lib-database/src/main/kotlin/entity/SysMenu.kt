package com.isyscore.wh.database.entity

import com.isyscore.wh.database.table.SysMenus
import com.isyscore.wh.database.table.SysPosts
import org.ktorm.dsl.QueryRowSet
import org.ktorm.entity.Entity
import java.time.LocalDate

interface SysMenu: Entity<SysMenu> {
    companion object : Entity.Factory<SysMenu>()
    var menuId: Long
    var menuName: String
    var parentId: Long
    var orderNum: Int
    var path: String
    var component: String?
    var isFrame: Int
    var isCache: Int
    var menuType: String
    var visible: String
    var status: String
    var perms: String?
    var icon: String
    var createBy: String
    var createTime: LocalDate?
    var updateBy: String
    var updateTime: LocalDate?
    var remark: String?
}
