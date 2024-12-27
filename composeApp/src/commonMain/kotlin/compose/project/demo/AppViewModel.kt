package compose.project.demo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class UiState(
    val showCountries: Boolean
) {
    companion object {
        fun initialValue(): UiState =
            UiState(
                showCountries = false
            )
    }
}

sealed interface Action {
    data class OnCountriesVisibilityToggle(val isVisible: Boolean) : Action
}

class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState.initialValue())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

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
