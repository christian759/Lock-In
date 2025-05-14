package com.grind.lockin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grind.lockin.data.db.AppDatabase
import com.grind.lockin.data.model.BlockedApp
import com.grind.lockin.utils.AppGlobals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LockInViewModel : ViewModel() {
    private val dao = AppDatabase.getDatabase(AppGlobals.getContext()).blockedAppDao()

    val blockedApps: Flow<List<BlockedApp>> = dao.getAll()

    fun removeBlockedApp(app: BlockedApp) {
        viewModelScope.launch {
            dao.delete(app)
        }
    }
}
