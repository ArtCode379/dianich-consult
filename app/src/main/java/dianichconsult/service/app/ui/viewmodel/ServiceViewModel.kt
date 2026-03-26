package dianichconsult.service.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dianichconsult.service.app.data.model.ServiceModel
import dianichconsult.service.app.data.repository.ServiceRepository
import dianichconsult.service.app.ui.state.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ServiceViewModel(
    private val serviceRepository: ServiceRepository
) : ViewModel() {
    private val _servicesState =
        MutableStateFlow<DataUiState<List<ServiceModel>>>(DataUiState.Initial)
    val servicesState: StateFlow<DataUiState<List<ServiceModel>>>
        get() = _servicesState.asStateFlow()

    init {
        observeServices()
    }

    private fun observeServices() {
        viewModelScope.launch {
            serviceRepository.observeAll().collect { services ->
                _servicesState.update {
                    DataUiState.from(services)
                }
            }
        }
    }
}