package com.example.testapplication.page

import com.example.testapplication.SelectorHelper.byStringText
import com.example.testapplication.element.ElementFabric.element
import io.qameta.allure.kotlin.Allure

class MainActivityPage : BasePage("Main activity", byStringText("TestApplication")) {

    val helloUiAutomator = element("Hello World!")

    fun assert(block: Asserts.() -> Unit): MainActivityPage = apply { Asserts().block() }

    inner class Asserts

    companion object {
        fun mainActivity(block: MainActivityPage.() -> Unit): MainActivityPage = MainActivityPage().apply { Allure.step(this.tag) { block() } }
    }
}
