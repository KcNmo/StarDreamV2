package com.starrywish.app.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "session")

class SessionStore(private val context: Context) {
    private val keyUid = stringPreferencesKey("uid")
    private val keyToken = stringPreferencesKey("token")

    val sessionFlow: Flow<Session?> = context.dataStore.data.map { prefs ->
        val uid = prefs[keyUid]
        val token = prefs[keyToken]
        if (uid != null && token != null) Session(uid, token) else null
    }

    suspend fun save(session: Session) {
        context.dataStore.edit { prefs: Preferences ->
            prefs[keyUid] = session.uid
            prefs[keyToken] = session.token
        }
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}
