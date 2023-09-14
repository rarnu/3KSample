package com.isyscore.wh.database.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.isyscore.wh.database.WHDatabase
import com.isyscore.wh.database.table.*
import org.ktorm.dsl.*
import org.ktorm.entity.Entity
import java.time.LocalDate

interface SysUser : Entity<SysUser> {
    companion object : Entity.Factory<SysUser>()

    var userId: Long // 用户ID
    var deptId: Long? // 部门ID
    var userName: String // 用户账号
    var nickName: String // 用户昵称
    var userType: String // 用户类型
    var email: String // 用户邮箱
    var phonenumber: String // 手机号码
    var sex: String //  用户性别
    var avatar: String // 用户头像

    @get:JsonIgnore
    var password: String // 密码
    var status: String // 帐号状态（0正常 1停用）
    var delFlag: String // 删除标志（0代表存在 2代表删除）
    var loginIp: String // 最后登录IP
    var loginDate: LocalDate? // 最后登录时间
    var createBy: String
    var createTime: LocalDate?
    var updateBy: String
    var updateTime: LocalDate?
    var remark: String?
    var reportAllProjects: String // 是否能在所有项目汇报(0=不能,1=能)

    // ref
    val dept: SysDept?  // 部门对象
}

val SysUser.roles: List<SysRole>    // 角色对象
    get() = WHDatabase.database.from(SysRoles)
        .leftJoin(SysUserRoles, on = SysRoles.roleId eq SysUserRoles.roleId)
        .select(SysRoles.columns).where {
            SysUserRoles.userId eq userId
        }.map {
            SysRoles.createEntity(it)
        }

val SysUser.posts: List<SysPost>    // 岗位组
    get() = WHDatabase.database.from(SysPosts)
        .leftJoin(SysUserPosts, on = SysPosts.postId eq SysUserPosts.postId)
        .select(SysPosts.columns).where {
            SysUserPosts.userId eq userId
        }.map {
            SysPosts.createEntity(it)
        }

val SysUser.menus: List<SysMenu> // 菜单权限
    get() = WHDatabase.database.from(SysMenus)
        .leftJoin(SysRoleMenus, on = SysMenus.menuId eq SysRoleMenus.menuId)
        .leftJoin(SysUserRoles, on = SysRoleMenus.roleId eq SysUserRoles.roleId)
        .select(SysMenus.columns).where {
            SysUserRoles.userId eq userId
        }.map { SysMenus.createEntity(it) }

val SysUser.perms: Set<String>
    get() = menus.filter { !it.perms.isNullOrEmpty() }.map { it.perms ?: "" }.toSet()

val SysUser.isAdmin: Boolean get() = this.userId == 1L