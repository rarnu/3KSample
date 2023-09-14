package com.isyscore.wh.routing

import com.isyscore.kotlin.ktor.plugin.withRoles
import com.isyscore.kotlin.ktor.requestParameters
import com.isyscore.wh.commo.response.AjaxResult
import com.isyscore.wh.database.WHDatabase
import com.isyscore.wh.database.table.sysDepts
import com.isyscore.wh.native.Demo
import com.isyscore.wh.session.SessionUser
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import org.ktorm.dsl.eq
import org.ktorm.entity.find

fun Routing.sample() {

    get("/hello") {
        val name = call.requestParameters()["name"] ?: "unknown"
        val helloName = Demo.sayHello(name)
        call.respond(AjaxResult.success(obj = helloName))
    }

    get("/sample/dept") {
        val deptId = (call.requestParameters()["deptId"] ?: "100").toLong()
        val d = WHDatabase.database.sysDepts.find { it.deptId eq deptId }
        call.respond(AjaxResult.success(d))
    }

    authenticate {

        withRoles("system:user:list") {

            get("/sample1") {
                val user = call.sessions.get<SessionUser>()
                call.sessions.set(user)
                call.respond(AjaxResult.success(11111))
            }
        }

        withRoles("sample:sample:sample") {

            get("/sample2") {
                call.respond(AjaxResult.success(222222))
            }
        }
    }

}

