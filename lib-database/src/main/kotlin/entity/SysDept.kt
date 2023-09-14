package com.isyscore.wh.database.entity

import com.isyscore.wh.database.WHDatabase
import com.isyscore.wh.database.table.sysDepts
import org.ktorm.dsl.eq
import org.ktorm.entity.Entity
import org.ktorm.entity.filter
import org.ktorm.entity.find
import org.ktorm.entity.toList
import java.time.LocalDate

interface SysDept: Entity<SysDept> {
    companion object : Entity.Factory<SysDept>()
    var deptId: Long    // 部门 ID
    var parentId: Long // 父部门ID
    var ancestors: String // 祖级列表
    var deptName: String // 部门名称
    var orderNum: Int // 显示顺序
    var leader: String? // 负责人
    var phone: String? // 联系电话
    var email: String? // 邮箱
    var status: String // 部门状态:0正常,1停用
    var delFlag: String // 删除标志（0代表存在 2代表删除）
    var createBy: String
    var createTime: LocalDate?
    var updateBy: String
    var updateTime: LocalDate?
}

val SysDept.parentName: String? // 父部门名称
    get() = WHDatabase.database.sysDepts.find { it.deptId eq parentId }?.deptName

val SysDept.children: List<SysDept> // 子部门
    get() = WHDatabase.database.sysDepts.filter { it.parentId eq deptId }.toList()