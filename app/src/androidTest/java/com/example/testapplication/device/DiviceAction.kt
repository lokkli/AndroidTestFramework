package com.example.testapplication.device

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import io.qameta.allure.android.allureScreenshot

val device: UiDevice
    get() = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

fun screenshot(tag: String = "before_action") {
    allureScreenshot(name = tag, quality = 50, scale = 1.0f)
}
