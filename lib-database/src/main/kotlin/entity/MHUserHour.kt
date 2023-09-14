package com.isyscore.wh.database.entity

import org.ktorm.entity.Entity
import java.time.LocalDate

interface MHUserHour: Entity<MHUserHour> {
    companion object : Entity.Factory<MHUserHour>()
    val id: Long
    var userId: Long
    var fillDate: LocalDate
    var totalHour: Double
    var createTime: LocalDate
}