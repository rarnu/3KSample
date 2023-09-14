package com.isyscore.wh

import com.isyscore.kotlin.ktor.*
import com.isyscore.wh.commo.response.AjaxResult
import com.isyscore.wh.config.loadDatabaseConfig
import com.isyscore.wh.database.WHDatabase
import com.isyscore.wh.database.entity.perms
import com.isyscore.wh.routing.rank
import com.isyscore.wh.routing.sample
import com.isyscore.wh.routing.user
import com.isyscore.wh.session.SessionUser
import com.isyscore.wh.system.UserMapper
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val dbCfg = loadDatabaseConfig()
    WHDatabase.initDatabase(dbCfg)
    pluginCORS()

    pluginSession<SessionUser>(
        isSecret = true,
        secretEncryptKey = "00112233445566778899aabbccddeeff",
        secretSignKey = "12345678"
    )

    pluginContentNegotiation()
    pluginAuthSession<SessionUser> {
        validate { it }
        challenge {
            call.respond(AjaxResult.error("必须先登录才能访问此接口"))
        }
    }

    pluginRoleAuthorization<SessionUser> {
        getRole {
            UserMapper.getInfo(it.userId)?.perms ?: setOf()
        }
        roleAuthFailed {
            respond(AjaxResult.error("您没有权限访问这个接口"))
        }
    }

    routing {
        user()
        rank()
        sample()
    }
}
