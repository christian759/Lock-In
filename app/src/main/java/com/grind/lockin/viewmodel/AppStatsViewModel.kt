package com.grind.lockin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grind.lockin.data.model.AppUsage
import com.grind.lockin.data.repo.AppUsageRepository
import kotlinx.coroutines.flow.*

class AppStatsViewModel(
    private val repository: AppUsageRepository = AppUsageRepository.getInstance()
) : ViewModel() {

    fun getTodayUsage(): StateFlow<Long> {
        return repository.getTodayUsage().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0L
        )
    }

    fun getUsageForApp(pkg: String): Flow<List<AppUsage>> {
        return repository.getUsageByPackage(pkg)
    }
}
