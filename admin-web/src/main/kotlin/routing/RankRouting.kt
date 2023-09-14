package com.isyscore.wh.routing

import com.isyscore.kotlin.ktor.requestParameters
import com.isyscore.wh.commo.response.AjaxResult
import com.isyscore.wh.rank.RankMapper
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun Routing.rank() = route("/rank") {
    authenticate {

        get("/efficiency/human") {
            val req = call.requestParameters()
            val startDate = LocalDate.parse(req["date"], DateTimeFormatter.ISO_DATE)
            val endDate = LocalDate.parse(req["endDate"], DateTimeFormatter.ISO_DATE)
            val count = (req["count"]?: "5").toInt()
            val result = RankMapper.getHumanEfficiency(count, startDate, endDate)
            call.respond(AjaxResult.success(result))
        }

    }
}