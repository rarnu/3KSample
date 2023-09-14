package com.isyscore.wh.database.entity

import com.isyscore.wh.database.table.SysPosts
import org.ktorm.dsl.QueryRowSet
import org.ktorm.entity.Entity
import java.time.LocalDate

interface SysPost: Entity<SysPost> {
    companion object : Entity.Factory<SysPost>()
    var postId: Long // 岗位序号
    var postCode: String // 岗位编码
    var postName: String    // 岗位名称
    var postSort: Int // 岗位排序
    var status: String // 状态（0正常 1停用）
    var flag: Boolean // 用户是否存在此岗位标识 默认不存在
    var createBy: String
    var createTime: LocalDate?
    var updateBy: String
    var updateTime: LocalDate?
    var remark: String?
}