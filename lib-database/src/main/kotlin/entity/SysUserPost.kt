package com.isyscore.wh.database.entity

import org.ktorm.entity.Entity

interface SysUserPost: Entity<SysUserPost> {
    companion object : Entity.Factory<SysUserPost>()
    var userId: Long
    var postId: Long
}