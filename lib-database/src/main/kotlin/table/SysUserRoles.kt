package com.isyscore.wh.database.table

import com.isyscore.wh.database.entity.SysUserRole
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.long

object SysUserRoles: Table<SysUserRole>("sys_user_role") {
    var userId = long("user_id").bindTo { it.userId }
    var roleId = long("role_id").bindTo { it.roleId }
}

val Database.sysUserRoles get() = this.sequenceOf(SysUserRoles)