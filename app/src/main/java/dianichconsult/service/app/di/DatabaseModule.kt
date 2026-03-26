package dianichconsult.service.app.di

import androidx.room.Room
import dianichconsult.service.app.data.database.ANCSLDatabase
import org.koin.dsl.module

private const val DB_NAME = "ancsl_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = ANCSLDatabase::class.java,
        name = DB_NAME
        ).build()
    }

    single { get<ANCSLDatabase>().bookingDao()}

}