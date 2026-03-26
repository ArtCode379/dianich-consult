package dianichconsult.service.app.di

import dianichconsult.service.app.data.repository.BookingRepository
import dianichconsult.service.app.data.repository.ANCSLOnboardingRepo
import dianichconsult.service.app.data.repository.ServiceRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        ANCSLOnboardingRepo(
            ancslOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ServiceRepository() }

    single{
        BookingRepository(
            bookingDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}