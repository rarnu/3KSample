package com.isyscore.wh.database.entity

import org.ktorm.entity.Entity

interface SysUserRole: Entity<SysUserRole> {
    companion object : Entity.Factory<SysUserRole>()
    var userId: Long
    var roleId: Long
}