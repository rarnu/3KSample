package com.isyscore.wh.database

import com.alibaba.druid.pool.DruidDataSourceFactory
import org.ktorm.database.Database
import org.ktorm.logging.ConsoleLogger
import org.ktorm.support.mysql.MySqlDialect
import java.util.*
import javax.sql.DataSource

object WHDatabase {

    lateinit var database: Database

    private lateinit var dataSource: DataSource

    /**
     * 使用前必须初始化，这里使用的是 Druid 连接池
     */
    fun initDatabase(config: DatabaseConfig) {
        val prop = Properties()
        config.javaClass.declaredFields.forEach {
            prop[it.name] = "${it.apply { isAccessible = true }.get(config)}"
        }
        dataSource = DruidDataSourceFactory.createDataSource(prop)
        database = Database.connect(
            dataSource = dataSource,
            dialect = MySqlDialect(),
            logger = ConsoleLogger(config.logLevel)
        )
    }
}