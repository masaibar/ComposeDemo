package compose.project.demo.di

import compose.project.demo.domain.country.CountryRepository
import compose.project.demo.domain.country.CountryRepositoryImpl
import compose.project.demo.ui.HomeViewmodel
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module

object Koin {

    private val appModule = module {
        // Repository
        single { CountryRepositoryImpl() } bind CountryRepository::class

        // ViewModel
        factory { HomeViewmodel(countryRepository = get()) }
    }

    fun init() {
        startKoin {
            modules(appModule)
        }
    }
}
