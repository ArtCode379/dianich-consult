package dianichconsult.service.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dianichconsult.service.app.data.dao.BookingDao
import dianichconsult.service.app.data.database.converter.Converters
import dianichconsult.service.app.data.entity.BookingEntity

@Database(
    entities = [BookingEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ANCSLDatabase : RoomDatabase() {

    abstract fun bookingDao(): BookingDao
}

