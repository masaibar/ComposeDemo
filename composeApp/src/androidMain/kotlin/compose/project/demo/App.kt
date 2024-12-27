package compose.project.demo

import android.app.Application
import compose.project.demo.di.Koin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Koin.init()
    }
}
