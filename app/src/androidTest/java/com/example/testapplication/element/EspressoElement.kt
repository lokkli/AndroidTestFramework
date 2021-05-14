package com.example.testapplication.element

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import com.example.testapplication.device.screenshot
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions
import io.qameta.allure.kotlin.Allure
import org.hamcrest.Matcher

class EspressoElement(private val matcher: Matcher<View>) : UIElement {
    operator fun invoke(block: EspressoElement.() -> Unit) = apply(block)

    override fun click() = Allure.step("Клик по элементу $matcher") {
        screenshot()
        Espresso.onView(matcher).perform(ViewActions.click())
    }

    override fun verifyElement() = Allure.step("Проверка отображения $matcher") {
        screenshot()
        BaristaVisibilityAssertions.assertDisplayed(matcher)
    }

    fun replaceText(text: String) = Allure.step("Замена текста на [$text]") {
        screenshot()
        Espresso.onView(matcher).perform(ViewActions.replaceText(text))
    }!!

    override fun toString() = matcher.toString()
}
