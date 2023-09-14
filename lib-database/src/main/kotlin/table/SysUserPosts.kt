package com.isyscore.wh.database.table

import com.isyscore.wh.database.entity.SysUserPost
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.long

object SysUserPosts: Table<SysUserPost>("sys_user_post") {
    var userId = long("user_id").bindTo { it.userId }
    var postId = long("post_id").bindTo { it.postId }
}

val Database.sysUserPosts get() = this.sequenceOf(SysUserPosts)