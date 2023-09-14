package com.isyscore.wh.rank.dto

import com.isyscore.wh.database.table.SysDepts
import com.isyscore.wh.database.table.SysUsers
import org.ktorm.dsl.QueryRowSet
import java.math.BigDecimal

data class RankHumanEfficiency(
    val userId: Long,
    val userName: String,
    val nickName: String,
    val deptName: String,
    val totalHours: BigDecimal
) {
    companion object
}

fun RankHumanEfficiency.Companion.createEntity(row: QueryRowSet): RankHumanEfficiency = RankHumanEfficiency(
    userId = row[SysUsers.userId] ?: -1L,
    userName = row[SysUsers.userName] ?: "",
    nickName = row[SysUsers.nickName] ?: "",
    deptName = row[SysDepts.deptName] ?: "",
    totalHours = row.getBigDecimal("totalHours") ?: BigDecimal.ZERO
)


