package com.touktw.github.base

import android.content.Context
import androidx.annotation.StringRes

data class StringResource(
    private val value: String? = null,
    @StringRes
    private val valueResId: Int? = null
) {
    fun get(context: Context? = null): String {
        return when {
            value != null -> value
            valueResId != null && context != null && valueResId != 0 -> context.getString(valueResId)
            else -> ""
        }
    }
}

sealed class UiEvent {
    data class Toast(val message: String?) : UiEvent()
    data class AlertDialog(
        val message: StringResource,
        val title: StringResource? = null,
        val positive: StringResource? = null,
        val positiveCallback: (suspend () -> Unit)?,
        val negative: StringResource? = null,
        val negativeCallback: (suspend () -> Unit)?
    )
}