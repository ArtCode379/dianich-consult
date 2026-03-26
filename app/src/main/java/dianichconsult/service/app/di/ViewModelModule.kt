package dianichconsult.service.app.di

import dianichconsult.service.app.ui.viewmodel.BookingViewModel
import dianichconsult.service.app.ui.viewmodel.CheckoutViewModel
import dianichconsult.service.app.ui.viewmodel.ANCSLOnboardingVM
import dianichconsult.service.app.ui.viewmodel.ServiceDetailsViewModel
import dianichconsult.service.app.ui.viewmodel.ServiceViewModel
import dianichconsult.service.app.ui.viewmodel.ANCSLSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        ANCSLSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ANCSLOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ServiceViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        ServiceDetailsViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        BookingViewModel(
            bookingRepository = get(),
            serviceRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            bookingRepository = get(),
        )
    }
}