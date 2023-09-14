package com.isyscore.wh.system

import com.isyscore.wh.common.request.ReqLogin
import com.isyscore.wh.database.WHDatabase
import com.isyscore.wh.database.entity.SysUser
import com.isyscore.wh.database.table.sysUsers
import org.ktorm.dsl.eq
import org.ktorm.entity.find
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

object UserMapper {

    fun login(req: ReqLogin): Pair<SysUser?, String> {
        val user = WHDatabase.database.sysUsers.find { it.userName eq req.userName } ?: return null to "用户不存在"
        val match = BCryptPasswordEncoder().matches(req.password, user.password)
        if (!match) {
            return null to "密码错误"
        }
        return user to ""
    }

    fun getInfo(userId: Long): SysUser? =
        WHDatabase.database.sysUsers.find { it.userId eq userId }

}