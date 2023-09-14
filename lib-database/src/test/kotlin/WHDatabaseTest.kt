package com.isyscore.wh.database

import com.isyscore.wh.database.entity.*
import com.isyscore.wh.database.table.SysDepts
import com.isyscore.wh.database.table.SysUsers
import com.isyscore.wh.database.table.sysDepts
import com.isyscore.wh.database.table.sysUsers
import org.ktorm.dsl.*
import org.ktorm.entity.Entity
import org.ktorm.entity.add
import org.ktorm.entity.find
import kotlin.test.Test

class WHDatabaseTest {

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
    fun testDept() {
        testConnect()

        val sd1 = WHDatabase.database.sysDepts.find { it.deptId eq 208 }
        println(sd1)
        println(sd1?.parentName)

        val sd2 = WHDatabase.database.sysDepts.find { it.deptId eq 100 }
        println(sd2)
        println(sd2?.parentName)
        println(sd2?.children)
    }

    @Test
    fun testUser() {
        testConnect()
        val su = WHDatabase.database.sysUsers.find { it.userId eq 131 }
        println(su)
        println(su?.roles)
        println(su?.posts)
    }

    @Test
    fun testUserInDept() {
        val deptName = "111"
        val list = WHDatabase.database.from(SysUsers).leftJoin(SysDepts, on = SysUsers.deptId eq SysDepts.deptId)
            .select(SysUsers.columns).where { SysDepts.deptName eq deptName }
            .map { SysUsers.createEntity(it) }
        list
    }
}