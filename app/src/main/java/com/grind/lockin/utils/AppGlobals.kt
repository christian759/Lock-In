package com.grind.lockin.utils

import android.content.Context

object AppGlobals {
    private lateinit var context: Context

    fun init(appContext: Context) {
        context = appContext.applicationContext
    }

    fun getContext(): Context = context
}
