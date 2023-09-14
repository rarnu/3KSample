package com.isyscore.wh.database.table

import com.isyscore.wh.database.entity.SysPost
import com.isyscore.wh.database.table.SysUserRoles.bindTo
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*

object SysPosts: Table<SysPost>("sys_post") {
    var postId = long("post_id").primaryKey().bindTo { it.postId }
    var postCode = varchar("post_code").bindTo { it.postCode }
    var postName = varchar("post_name").bindTo { it.postName }
    var postSort = int("post_sort").bindTo { it.postSort }
    var status = varchar("status").bindTo { it.status }
    var createBy = varchar("create_by").bindTo { it.createBy }
    var createTime = date("create_time").bindTo { it.createTime }
    var updateBy = varchar("update_by").bindTo { it.updateBy }
    var updateTime = date("update_time").bindTo { it.updateTime }
    var remark = varchar("remark").bindTo { it.remark }
}

val Database.sysPosts get() = this.sequenceOf(SysPosts)