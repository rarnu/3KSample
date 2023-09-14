package com.isyscore.wh.database.entity

import com.isyscore.wh.database.table.SysRoles
import org.ktorm.dsl.QueryRowSet
import org.ktorm.entity.Entity
import java.time.LocalDate

interface SysRole: Entity<SysRole> {
    companion object : Entity.Factory<SysRole>()
    var roleId: Long    // 角色序号
    var roleName: String // 角色名称
    var roleKey: String // 角色权限
    var roleSort : Int // 角色排序
    var dataScope: String // 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）
    var menuCheckStrictly: Boolean // 菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示）
    var deptCheckStrictly: Boolean // 部门树选择项是否关联显示（0：父子不互相关联显示 1：父子互相关联显示 ）
    var status: String // 角色状态（0正常 1停用）
    var delFlag: String // 删除标志（0代表存在 2代表删除）
    var createBy: String
    var createTime: LocalDate?
    var updateBy: String
    var updateTime: LocalDate?
    var remark: String?
    var flag: Boolean // 用户是否存在此角色标识 默认不存在

    // TODO: 菜单组/部门组
    /*

    /**
     * 菜单组
     */
    private Long[] menuIds;

    /**
     * 部门组（数据权限）
     */
    private Long[] deptIds;

     */
}