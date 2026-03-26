package dianichconsult.service.app.data.repository

import dianichconsult.service.app.data.datastore.ANCSLOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ANCSLOnboardingRepo(
    private val ancslOnboardingStoreManager: ANCSLOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return ancslOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            ancslOnboardingStoreManager.setOnboardedState(state)
        }
    }
}