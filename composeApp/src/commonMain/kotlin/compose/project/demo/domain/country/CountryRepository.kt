package compose.project.demo.domain.country

interface CountryRepository {

    suspend fun findAll(): List<Country>
}
