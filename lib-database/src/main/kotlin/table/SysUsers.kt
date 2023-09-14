package com.isyscore.wh.database.table

import com.isyscore.wh.database.entity.SysUser
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*

object SysUsers : Table<SysUser>("sys_user") {
    var userId = long("user_id").primaryKey().bindTo { it.userId }
    var deptId = long("dept_id").references(SysDepts) { it.dept }.bindTo { it.deptId }
    var userName = varchar("user_name").bindTo { it.userName }
    var nickName = varchar("nick_name").bindTo { it.nickName }
    var userType = varchar("user_type").bindTo { it.userType }
    var email = varchar("email").bindTo { it.email }
    var phonenumber = varchar("phonenumber").bindTo { it.phonenumber }
    var sex = varchar("sex").bindTo { it.sex }
    var avatar = varchar("avatar").bindTo { it.avatar }
    var password = varchar("password").bindTo { it.password }
    var status = varchar("status").bindTo { it.status }
    var delFlag = varchar("del_flag").bindTo { it.delFlag }
    var loginIp = varchar("login_ip").bindTo { it.loginIp }
    var loginDate = date("login_date").bindTo { it.loginDate }
    var createBy = varchar("create_by").bindTo { it.createBy }
    var createTime = date("create_time").bindTo { it.createTime }
    var updateBy = varchar("update_by").bindTo { it.updateBy }
    var updateTime = date("update_time").bindTo { it.updateTime }
    var remark = text("remark").bindTo { it.remark }
    var reportAllProjects = varchar("report_all_projects").bindTo { it.reportAllProjects }
}

val Database.sysUsers get() = this.sequenceOf(SysUsers)