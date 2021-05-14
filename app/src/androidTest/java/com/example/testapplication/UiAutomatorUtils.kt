package com.example.testapplication

import androidx.annotation.AnyRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import com.example.testapplication.ResourceConverter.resourceName
import com.example.testapplication.ResourceConverter.stringRes
import com.example.testapplication.TestContextHelper.targetContext
import com.example.testapplication.device.device
import com.example.testapplication.exception.BySelectorNotFoundException


object ResourceConverter {
    @JvmStatic
    fun stringRes(@StringRes resId: Int): String {
        return targetContext.resources.getString(resId)
    }

    @JvmStatic
    fun stringRes(@StringRes resId: Int, vararg formatArgs: Any): String {
        return targetContext.resources.getString(resId, *formatArgs)
    }

    @JvmStatic
    fun resourceName(@AnyRes resId: Int): String {
        return targetContext.resources.getResourceName(resId)
    }

    @JvmStatic
    fun stringArray(@AnyRes resId: Int): Array<String> {
        return targetContext.resources.getStringArray(resId)
    }
}

object SelectorHelper {
    @JvmStatic
    fun byRes(@IdRes resId: Int): BySelector {
        return By.res(resourceName(resId))
    }

    @JvmStatic
    fun byStringRes(resString: String): BySelector {
        return By.res(resString)
    }

    @JvmStatic
    fun byResString(@StringRes resId: Int): BySelector {
        return By.text(stringRes(resId))
    }

    @JvmStatic
    fun byStringText(text: String): BySelector {
        return By.text(text)
    }

    @JvmStatic
    fun byContentDesc(text: String): BySelector {
        return By.desc(text)
    }

    @JvmStatic
    fun byContentDescContains(text: String): BySelector {
        return By.descContains(text)
    }

    @JvmStatic
    fun byResContentDesc(@StringRes resId: Int): BySelector {
        return By.desc(stringRes(resId))
    }

    @JvmStatic
    fun byTextContains(text: String): BySelector {
        return By.textContains(text)
    }

    @JvmStatic
    fun byClazz(clazz: Class<*>): BySelector {
        return By.clazz(clazz)
    }

    fun byDescriptionContains(description: String): UiSelector {
        return UiSelector().descriptionContains(description)
    }
}

object ScrollHelper {

    fun scrollToText(text: String): Boolean =
        UiScrollable(UiSelector()).scrollTextIntoView(text)

    fun scrollToId(id: Int): Boolean =
        UiScrollable(UiSelector()).scrollIntoView(UiSelector().resourceId(resourceName(id)))

    fun scrollToResString(resString: Int): Boolean =
        UiScrollable(UiSelector()).scrollTextIntoView(stringRes(resString))

    fun scrollToDescription(text: String): Boolean =
        UiScrollable(UiSelector().scrollable(true)).scrollDescriptionIntoView(text)

    fun scrollToTextContains(text: String): Boolean =
        UiScrollable(UiSelector()).scrollIntoView(UiSelector().textContains(text))
}

/** Extension functions */

fun BySelector.hasObject(): Boolean = device.hasObject(this)

fun BySelector.waitHasObject(): Boolean = device waitHas this

fun BySelector.findObject(): UiObject2 = device.findObject(this) ?: throw BySelectorNotFoundException(this)

fun BySelector.waitFindObject(): UiObject2 = device waitFind this

fun BySelector.waitGone(): Boolean = device waitGone this

fun BySelector.waitFindMany(): List<UiObject2> = device waitFindMany this

fun BySelector.waitTimeoutFindObject(timeout: Long = 5.seconds.milliseconds): UiObject2 = device.wait(Until.findObject(this), timeout)
    ?: throw BySelectorNotFoundException(this)

fun BySelector.waitTimeoutHasObject(timeout: Long = 5.seconds.milliseconds): Boolean = device.wait(Until.hasObject(this), timeout)

fun UiObject2.clickAndWaitNewWindow(): Boolean = this.clickAndWait(Until.newWindow(), 5.seconds.milliseconds)

/** Infix functions */

infix fun UiDevice.waitFind(selector: BySelector): UiObject2 = this.wait(Until.findObject(selector), 5.seconds.milliseconds)
    ?: throw BySelectorNotFoundException(selector)

infix fun UiDevice.waitFindMany(selector: BySelector): List<UiObject2> = this.wait(Until.findObjects(selector), 5.seconds.milliseconds)

infix fun UiDevice.waitHas(selector: BySelector): Boolean = this.wait(Until.hasObject(selector), 5.seconds.milliseconds)

infix fun UiDevice.waitGone(selector: BySelector): Boolean = this.wait(Until.gone(selector), 5.seconds.milliseconds)

infix fun UiObject2.waitFind(selector: BySelector): UiObject2 = this.wait(Until.findObject(selector), 5.seconds.milliseconds)
    ?: throw BySelectorNotFoundException(selector)

infix fun UiObject2.findObject(selector: BySelector): UiObject2 = this.findObject(selector) ?: throw BySelectorNotFoundException(selector)

infix fun UiObject2.hasObject(selector: BySelector): Boolean = this.hasObject(selector)

infix fun UiObject2.waitHas(selector: BySelector): Boolean = this.wait(Until.hasObject(selector), 5.seconds.milliseconds)

infix fun UiObject2.waitFindMany(selector: BySelector): List<UiObject2> = this.wait(Until.findObjects(selector), 5.seconds.milliseconds)

infix fun UiObject2.findMany(selector: BySelector): List<UiObject2> = this.findObjects(selector)

infix fun BySelector.waitFind(selector: BySelector): UiObject2 = this.waitFindObject() waitFind selector

infix fun BySelector.findObject(selector: BySelector): UiObject2 = this.waitFindObject() findObject selector

infix fun BySelector.waitFindMany(selector: BySelector): List<UiObject2> = this.waitFindObject() waitFindMany selector

infix fun BySelector.findMany(selector: BySelector): List<UiObject2> = this.waitFindObject() findMany selector

infix fun BySelector.hasObject(selector: BySelector): Boolean = this.findObject() hasObject selector

infix fun BySelector.waitHas(selector: BySelector): Boolean = this.waitFindObject() waitHas selector
