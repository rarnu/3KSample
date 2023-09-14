package com.isyscore.wh.rank

import com.isyscore.wh.database.DatabaseConfig
import com.isyscore.wh.database.WHDatabase
import java.time.LocalDate
import java.time.Month
import kotlin.test.Test

class RankMapperTest {

    private fun testConnect() {
        val config = DatabaseConfig(
            driverClassName = "com.mysql.cj.jdbc.Driver",
            url = "jdbc:mysql://127.0.0.1:3306/wuyu_cost?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8",
            username = "root",
            password = "rootroot",
            initialSize = 10,
            maxActive = 25,
            maxWait = 3000)
        WHDatabase.initDatabase(config)
    }

    @Test
    fun testRank() {
        testConnect()
        val start = LocalDate.of(2023, Month.AUGUST, 18)
        val end = LocalDate.of(2023, Month.AUGUST, 19)
        val result = RankMapper.getHumanEfficiency(10, start, end)
        println(result)
    }
}