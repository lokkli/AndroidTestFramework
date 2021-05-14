package com.example.testapplication.element

import androidx.test.uiautomator.BySelector
import com.example.testapplication.SelectorHelper.byStringText
import com.example.testapplication.device.screenshot
import com.example.testapplication.waitFindObject
import com.example.testapplication.waitHasObject
import io.qameta.allure.kotlin.Allure
import org.junit.Assert

class UIAutomatorElement(
    private val text: String? = null,
    private val selector: BySelector? = null,
) : UIElement {

    override fun click() = Allure.step("Клик по элементу $this") {
        screenshot()
        when {
            this@UIAutomatorElement.text != null -> byStringText(this@UIAutomatorElement.text).waitFindObject().click()
            this@UIAutomatorElement.selector != null -> selector.waitFindObject().click()
            else -> throw IllegalStateException()
        }
    }

    override fun verifyElement() {
        when {
            this.text != null -> verifyTextUIAutomator(text)
            this.selector != null -> verifyBySelector(selector)
        }
    }

    private fun verifyTextUIAutomator(textToVerify: String) = Allure.step("Проверка наличия текста: '$textToVerify'") {
        Assert.assertTrue("Текст '$textToVerify' не отобразился", byStringText(textToVerify).waitHasObject())
    }

    private fun verifyBySelector(selector: BySelector) = Allure.step("Проверка отображения элемента $this") {
        Assert.assertTrue("Элемент по селектору $this не отобразился", selector.waitHasObject())
    }


    override fun toString() = when {
        selector != null -> selector.toString()
        text != null -> text
        else -> throw IllegalStateException()
    }
}
