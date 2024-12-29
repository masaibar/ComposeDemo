package compose.project.demo.di

import compose.project.demo.defaultDispatcher
import compose.project.demo.domain.country.CountryRepository
import compose.project.demo.domain.country.CountryRepositoryImpl
import compose.project.demo.domain.greeting.Greeting
import compose.project.demo.ioDispatcher
import compose.project.demo.ui.main.MainViewmodel
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module

object Koin {

    private val appModule = module {
        single { ioDispatcher }
        single { defaultDispatcher }

        single {
            CountryRepositoryImpl(
                ioDispatcher = get()
            )
        } bind CountryRepository::class
        single { Greeting() }

        // ViewModel
        factory {
            MainViewmodel(
                greeting = get(),
                countryRepository = get()
            )
        }
    }

    fun init() {
        startKoin {
            modules(appModule)
        }
    }
}
