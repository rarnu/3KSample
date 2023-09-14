package com.isyscore.wh.database.table

import com.isyscore.wh.database.entity.MHUserHour
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*

object MHUserHours: Table<MHUserHour>("mh_user_hour") {
    var id = long("id").primaryKey().bindTo { it.id }
    var userId = long("user_id").bindTo { it.userId }
    var fillDate = date("fill_date").bindTo { it.fillDate }
    var totalHour = double("total_hour").bindTo { it.totalHour }
    var createTime = date("create_time").bindTo { it.createTime }
}

val Database.mhUserHours get() = this.sequenceOf(MHUserHours)
val Database.mhUserHours2 get() = this.from(MHUserHours)