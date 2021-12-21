package com.core.resource

import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider(val context: Context) {
    fun getString(@StringRes resId: Int): String =
        context.resources.getString(resId)
}