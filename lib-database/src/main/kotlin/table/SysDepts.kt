package com.isyscore.wh.database.table

import com.isyscore.wh.database.entity.SysDept
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*

object SysDepts : Table<SysDept>("sys_dept") {
    var deptId = long("dept_id").primaryKey().bindTo { it.deptId }
    var parentId = long("parent_id").bindTo { it.parentId }
    var ancestors = varchar("ancestors").bindTo { it.ancestors }
    var deptName = varchar("dept_name").bindTo { it.deptName }
    var orderNum = int("order_num").bindTo { it.orderNum }
    var leader = varchar("leader").bindTo { it.leader }
    var phone = varchar("phone").bindTo { it.phone }
    var email = varchar("email").bindTo { it.email }
    var status = varchar("status").bindTo { it.status }
    var delFlag = varchar("del_flag").bindTo { it.delFlag }
    var createBy = varchar("create_by").bindTo { it.createBy }
    var createTime = date("create_time").bindTo { it.createTime }
    var updateBy = varchar("update_by").bindTo { it.updateBy }
    var updateTime = date("update_time").bindTo { it.updateTime }
}

val Database.sysDepts get() = this.sequenceOf(SysDepts)