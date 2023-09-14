package com.isyscore.wh.database

import org.ktorm.logging.LogLevel

data class DatabaseConfig(
    val driverClassName: String,    // 驱动的类名
    val url: String,    // jdbc url
    val username: String, // 用户名
    val password: String, // 密码
    val initialSize: Int = 10, // 默认连接数
    val maxActive: Int = 25, // 最大连接数
    val maxWait: Long = 3000, // 最大等待时间
    val logLevel: LogLevel = LogLevel.DEBUG // 输出的日志级别
)
