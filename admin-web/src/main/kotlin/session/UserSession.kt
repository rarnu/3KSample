package com.isyscore.wh.session

import com.isyscore.wh.database.entity.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.sessions.*
import io.ktor.util.pipeline.*
import java.time.LocalDate

data class SessionUser(
    val userId: Long = -1, // 用户ID
    val deptId: Long? = null, // 部门ID
    val userName: String = "", // 用户账号
    val nickName: String = "", // 用户昵称
    val deptName: String = "", // 部门名称
    val loginDate: LocalDate? = null,
) : Principal {
    companion object {
        fun fromSysUser(u: SysUser?): SessionUser =
            if (u == null) {
                SessionUser(-1)
            } else {
                SessionUser(
                    userId = u.userId,
                    deptId = u.deptId,
                    userName = u.userName,
                    nickName = u.nickName,
                    deptName = u.dept?.deptName ?: "",
                    loginDate = u.loginDate
                )
            }
    }
}

inline var PipelineContext<*, ApplicationCall>.user: SessionUser?
    get() = context.sessions.get()
    set(value) = context.sessions.set(value)

