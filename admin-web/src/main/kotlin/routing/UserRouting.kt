package com.isyscore.wh.routing

import com.isyscore.wh.commo.response.AjaxResult
import com.isyscore.wh.common.request.ReqLogin
import com.isyscore.wh.session.SessionUser
import com.isyscore.wh.session.user
import com.isyscore.wh.system.UserMapper
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.user() = route("/user") {
    post<ReqLogin>("/login") {
        val (u, err) = UserMapper.login(it)
        if (u == null) {
            call.respond(AjaxResult.error(err))
            return@post
        }
        val su = SessionUser.fromSysUser(u)
        user = su
        call.respond(AjaxResult.success(su))
    }

    authenticate {

        get("/info") {
            val u = UserMapper.getInfo(user?.userId ?: -1)
            call.respond(AjaxResult.success(u))
        }

        get("/logout") {
            user = null
            call.respond(AjaxResult.success())
        }
    }
}
