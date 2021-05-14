package com.example.testapplication.element

import android.view.View
import androidx.test.uiautomator.BySelector
import org.hamcrest.Matcher

object ElementFabric {
    fun element(text: String) = UIAutomatorElement(text, null)
    fun element(matcher: Matcher<View>) = EspressoElement(matcher)
    fun element(selector: BySelector) = UIAutomatorElement(null, selector)

    fun verifyElement(element: UIElement) = element.verifyElement()
}
