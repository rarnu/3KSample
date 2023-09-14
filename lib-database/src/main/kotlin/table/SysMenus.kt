package com.isyscore.wh.database.table

import com.isyscore.wh.database.entity.SysMenu
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*

object SysMenus : Table<SysMenu>("sys_menu") {
    var menuId = long("menu_id").primaryKey().bindTo { it.menuId }
    var menuName = varchar("menu_name").bindTo { it.menuName }
    val parentId = long("parent_id").bindTo { it.parentId }
    var orderNum = int("order_num").bindTo { it.orderNum }
    var path = varchar("path").bindTo { it.path }
    var component = varchar("component").bindTo { it.component }
    var isFrame = int("is_frame").bindTo { it.isFrame }
    var isCache = int("is_cache").bindTo { it.isCache }
    var menuType = varchar("menu_type").bindTo { it.menuType }
    var visible = varchar("visible").bindTo { it.visible }
    var status = varchar("status").bindTo { it.status }
    var perms = varchar("perms").bindTo { it.perms }
    var icon = varchar("icon").bindTo { it.icon }
    var createBy = varchar("create_by").bindTo { it.createBy }
    var createTime = date("create_time").bindTo { it.createTime }
    var updateBy = varchar("update_by").bindTo { it.updateBy }
    var updateTime = date("update_time").bindTo { it.updateTime }
    var remark = varchar("remark").bindTo { it.remark }
}

val Database.sysMenus get() = this.sequenceOf(SysMenus)