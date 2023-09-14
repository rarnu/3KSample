package com.isyscore.wh.system

import com.isyscore.wh.common.request.ReqLogin
import com.isyscore.wh.database.DatabaseConfig
import com.isyscore.wh.database.WHDatabase
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import kotlin.test.Test

class CryptoTest {

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
    fun testCrypto() {
        val enc = BCryptPasswordEncoder()
        val pwd = enc.encode("12345678")
        println(pwd)

        val p1 = "\$2a\$10\$EQPJw.3JaXxrjkI7XlrAbuB5TmdyRK.SXiaPMRyIglmx6aXM38p5G"
        val p2 = "\$2a\$10\$X00l.Qs8WiAeN84c3LxsdOdF4bSy069WnFXdQA/JSG9ZXFCG42VLS"
        val m1 = enc.matches("12345678", p1)
        val m2 = enc.matches("12345678", p2)
        println("m1 = $m1, m2 = $m2")
    }

    @Test
    fun testLogin() {
        testConnect()
        UserMapper.login(ReqLogin("admin", "12345678"))
    }
}