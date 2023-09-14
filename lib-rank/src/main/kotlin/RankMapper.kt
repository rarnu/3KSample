package com.isyscore.wh.rank

import com.isyscore.wh.database.WHDatabase
import com.isyscore.wh.database.table.MHUserHours
import com.isyscore.wh.database.table.SysDepts
import com.isyscore.wh.database.table.SysUsers
import com.isyscore.wh.rank.dto.RankHumanEfficiency
import com.isyscore.wh.rank.dto.createEntity
import org.ktorm.dsl.*
import java.time.LocalDate

object RankMapper {

    fun getHumanEfficiency(count: Int, startDate: LocalDate, endDate: LocalDate): List<RankHumanEfficiency> = WHDatabase.database
        .from(SysUsers)
        .leftJoin(SysDepts, on = SysUsers.deptId eq SysDepts.deptId)
        .leftJoin(MHUserHours, on = SysUsers.userId eq MHUserHours.userId)
        .select(SysUsers.userId, SysUsers.userName, SysUsers.nickName, SysDepts.deptName, sum(MHUserHours.totalHour).aliased("totalHours"))
        .where { MHUserHours.fillDate between startDate..endDate }.groupBy(SysUsers.userId)
        .orderBy(sum(MHUserHours.totalHour).desc())
        .limit(0, count)
        .map { RankHumanEfficiency.createEntity(it) }


    fun getHumanEfficiencyUsers(count: Int, startDate: LocalDate, endDate: LocalDate, userIds: List<Long>? = null): List<RankHumanEfficiency> = WHDatabase.database
        .from(SysUsers)
        .leftJoin(SysDepts, on = SysUsers.deptId eq SysDepts.deptId)
        .leftJoin(MHUserHours, on = SysUsers.userId eq MHUserHours.userId)
        .select(SysUsers.userId, SysUsers.userName, SysUsers.nickName, SysDepts.deptName, sum(MHUserHours.totalHour).aliased("totalHours"))
        .whereWithConditions {
            it += MHUserHours.fillDate between startDate..endDate
            if (!userIds.isNullOrEmpty()) {
                it += SysUsers.userId inList userIds
            }
        }
        .groupBy(SysUsers.userId)
        .orderBy(sum(MHUserHours.totalHour).desc())
        .limit(0, count)
        .map { RankHumanEfficiency.createEntity(it) }

    fun getHumanEfficiencyDept(count: Int, startDate: LocalDate, endDate: LocalDate, deptId: Long): List<RankHumanEfficiency> = WHDatabase.database
        .from(SysUsers)
        .leftJoin(SysDepts, on = SysUsers.deptId eq SysDepts.deptId)
        .leftJoin(MHUserHours, on = SysUsers.userId eq MHUserHours.userId)
        .select(SysUsers.userId, SysUsers.userName, SysUsers.nickName, SysDepts.deptName, sum(MHUserHours.totalHour).aliased("totalHours"))
        .where { (MHUserHours.fillDate between startDate..endDate) and (SysDepts.deptId eq deptId) }
        .groupBy(SysUsers.userId)
        .orderBy(sum(MHUserHours.totalHour).desc())
        .limit(0, count)
        .map { RankHumanEfficiency.createEntity(it) }

}



