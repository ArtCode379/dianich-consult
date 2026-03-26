package dianichconsult.service.app

import android.app.Application
import dianichconsult.service.app.di.dataModule
import dianichconsult.service.app.di.dispatcherModule
import dianichconsult.service.app.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class ServiceApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@ServiceApp)
            modules(appModules)
        }
    }
}