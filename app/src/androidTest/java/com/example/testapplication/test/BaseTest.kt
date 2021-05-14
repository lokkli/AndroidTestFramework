package com.example.testapplication.test

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.example.testapplication.MainActivity
import io.qameta.allure.android.rules.LogcatRule
import io.qameta.allure.android.rules.ScreenshotRule
import org.junit.Rule
import org.junit.rules.RuleChain

abstract class BaseTest {
    private val activityRule get() = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val ruleChain: RuleChain = RuleChain
        .outerRule(activityRule)
        .around(ScreenshotRule())
        .around(LogcatRule())
}
