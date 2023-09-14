package com.isyscore.wh

import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer
import com.isyscore.kotlin.common.toJson
import com.isyscore.wh.database.DatabaseConfig
import com.isyscore.wh.database.WHDatabase
import com.isyscore.wh.database.entity.SysDept
import com.isyscore.wh.session.SessionUser
import com.isyscore.wh.system.UserMapper
import org.junit.Test
import org.ktorm.jackson.KtormModule
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ApplicationTest {
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
    fun testJackson() {
        testConnect()
        val user = UserMapper.getInfo(131)
        val su = SessionUser.fromSysUser(user)

        val localDatePattern: String = "yyyy-MM-dd"
        val localTimePattern: String = "hh:mm:ss"
        val localDateTimePattern: String = "yyyy-MM-dd hh:mm:ss"
        val om = ObjectMapper().apply {
            registerModule(KtormModule())
            registerModule(JavaTimeModule().apply {
                addDeserializer(LocalDate::class.java, LocalDateDeserializer(DateTimeFormatter.ofPattern(localDatePattern)))
                addSerializer(LocalDate::class.java, LocalDateSerializer(DateTimeFormatter.ofPattern(localDatePattern)))
                addDeserializer(LocalTime::class.java, LocalTimeDeserializer(DateTimeFormatter.ofPattern(localTimePattern)))
                addSerializer(LocalTime::class.java, LocalTimeSerializer(DateTimeFormatter.ofPattern(localTimePattern)))
                addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(localDateTimePattern)))
                addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer(DateTimeFormatter.ofPattern(localDateTimePattern)))
            })
            configure(SerializationFeature.INDENT_OUTPUT, true)
            setDefaultLeniency(true)
            setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
                indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                indentObjectsWith(DefaultIndenter("  ", "\n"))
            })
        }
        val json = om.writeValueAsString(su)
        println(json)
    }
}
