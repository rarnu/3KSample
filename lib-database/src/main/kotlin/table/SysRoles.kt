package com.isyscore.wh.database.table

import com.isyscore.wh.database.entity.SysRole
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*

object SysRoles: Table<SysRole>("sys_role") {
    var roleId = long("role_id").primaryKey().bindTo { it.roleId }
    var roleName = varchar("role_name").bindTo { it.roleName }
    var roleKey = varchar("role_key").bindTo { it.roleKey }
    var roleSort = int("role_sort").bindTo { it.roleSort }
    var dataScope = varchar("data_scope").bindTo { it.dataScope }
    var menuCheckStrictly = int("menu_check_strictly").bindTo { if (it.menuCheckStrictly) 1 else 0 }
    var deptCheckStrictly = int("dept_check_strictly").bindTo { if (it.deptCheckStrictly) 1 else 0 }
    var status = varchar("status").bindTo { it.status }
    var delFlag = varchar("del_flag").bindTo { it.delFlag }
    var createBy = varchar("create_by").bindTo { it.createBy }
    var createTime = date("create_time").bindTo { it.createTime }
    var updateBy = varchar("update_by").bindTo { it.updateBy }
    var updateTime = date("update_time").bindTo { it.updateTime }
    var remark = varchar("remark").bindTo { it.remark }
}

val Database.sysRoles get() = this.sequenceOf(SysRoles)