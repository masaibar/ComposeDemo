package compose.project.demo.domain.country

import composedemo.composeapp.generated.resources.Res
import composedemo.composeapp.generated.resources.eg
import composedemo.composeapp.generated.resources.fr
import composedemo.composeapp.generated.resources.id
import composedemo.composeapp.generated.resources.jp
import composedemo.composeapp.generated.resources.mx
import kotlinx.datetime.TimeZone

class CountryRepositoryImpl : CountryRepository {

    override suspend fun findAll(): List<Country> = listOf(
        Country(
            name = "Japan",
            zone = TimeZone.of("Asia/Tokyo"),
            image = Res.drawable.jp
        ),
        Country(
            name = "France",
            zone = TimeZone.of("Europe/Paris"),
            image = Res.drawable.fr
        ),
        Country(
            name = "Mexico",
            zone = TimeZone.of("America/Mexico_City"),
            image = Res.drawable.mx
        ),
        Country(
            name = "Indonesia",
            zone = TimeZone.of("Asia/Jakarta"),
            image = Res.drawable.id
        ),
        Country(
            name = "Egypt",
            zone = TimeZone.of("Africa/Cairo"),
            image = Res.drawable.eg
        )
    )
}
