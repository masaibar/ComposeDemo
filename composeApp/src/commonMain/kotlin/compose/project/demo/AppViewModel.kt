package compose.project.demo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import compose.project.demo.domain.country.Country
import compose.project.demo.domain.country.CountryRepository
import compose.project.demo.domain.country.CountryRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UiState(
    val countries: List<Country>,
    val showCountries: Boolean,
) {
    companion object {
        fun initialValue(): UiState =
            UiState(
                countries = emptyList(),
                showCountries = false
            )
    }
}

sealed interface Action {
    data class OnCountriesVisibilityToggle(val isVisible: Boolean) : Action
}

class AppViewModel(
    private val countryRepository: CountryRepository = CountryRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState.initialValue())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(countries = countryRepository.findAll())
            }
        }
    }

    fun onAction(action: Action) {
        when (action) {
            is Action.OnCountriesVisibilityToggle -> {
                _uiState.update {
                    it.copy(showCountries = action.isVisible)
                }
            }
        }
    }
}
