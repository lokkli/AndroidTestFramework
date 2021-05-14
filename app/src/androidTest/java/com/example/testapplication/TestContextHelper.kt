package com.example.testapplication

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry

object TestContextHelper {
    val context: Context = InstrumentationRegistry.getInstrumentation().context
    val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
}
