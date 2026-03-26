package dianichconsult.service.app.di

import dianichconsult.service.app.data.datastore.ANCSLOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { ANCSLOnboardingPrefs(androidContext()) }
}