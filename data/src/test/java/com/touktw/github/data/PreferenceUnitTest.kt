package com.touktw.github.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.touktw.github.data.source.local.datastore.PreferenceDataStore
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PreferenceUnitTest {
    companion object {
        private const val EXAMPLE_TOKEN = "123456789"
    }

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun token_set_get() {
        runTest {
            PreferenceDataStore.setToken(context, EXAMPLE_TOKEN)
            val token = PreferenceDataStore.getToken(context)
            assertEquals(EXAMPLE_TOKEN, token)
        }
    }
}