package com.touktw.github.data.source.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

object PreferenceDataStore {
    private const val PREFERENCE_NAME = "github_reader_preference"

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

    private object PreferencesKeys {
        val TOKEN = stringPreferencesKey("token")
    }

    suspend fun setToken(context: Context, token: String?) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.TOKEN] = token ?: ""
        }
    }

    suspend fun getToken(context: Context): String? {
        return context.dataStore.data.first()[PreferencesKeys.TOKEN]
    }
}