package com.example.testapplication.page

import androidx.test.uiautomator.BySelector
import com.example.testapplication.TestConstants.WAIT_TIME
import com.example.testapplication.waitTimeoutHasObject
import java.lang.Thread.sleep

abstract class BasePage(val tag: String, initSelector: BySelector? = null) {
    private var time: Long? = null

    constructor(tag: String, time: Long) : this(tag) {
        this.time = time
        sleep(time)
    }

    init {
        initSelector?.waitTimeoutHasObject(WAIT_TIME)
    }
}
