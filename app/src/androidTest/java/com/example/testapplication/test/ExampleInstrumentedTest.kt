package com.example.testapplication.test

import com.example.testapplication.page.MainActivityPage.Companion.mainActivity
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.junit4.DisplayName
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AllureAndroidJUnit4::class)
class ExampleInstrumentedTest : BaseTest() {
    @Test
    @DisplayName("Launch app test")
    fun simpleHelloWorldTest() {
        mainActivity {
            assert { helloUiAutomator.verifyElement() }
        }
    }
}
