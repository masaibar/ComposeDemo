package compose.project.demo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import compose.project.demo.domain.country.Country
import compose.project.demo.domain.country.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UiState(
    val countries: List<Country>,
    val selectedCountry: Country?,
    val showCountries: Boolean,
) {
    companion object {
        fun initialValue(): UiState =
            UiState(
                countries = emptyList(),
                selectedCountry = null,
                showCountries = false
            )
    }
}

sealed interface Action {
    data object OnSelectLocationClick : Action
    data object OnDropdownMenuDismiss : Action
    data class OnCountrySelected(val country: Country) : Action
}

class HomeViewmodel(
    private val countryRepository: CountryRepository
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
            Action.OnSelectLocationClick -> {
                _uiState.update {
                    it.copy(showCountries = true)
                }
            }

            Action.OnDropdownMenuDismiss -> {
                _uiState.update {
                    it.copy(showCountries = false)
                }
            }

            is Action.OnCountrySelected -> {
                _uiState.update {
                    it.copy(
                        selectedCountry = action.country,
                        showCountries = false
                    )
                }
            }
        }
    }
}
