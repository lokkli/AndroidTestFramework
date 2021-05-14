package com.example.testapplication.exception

import androidx.test.uiautomator.BySelector

/**
 * @author Urmancheev Stanislav
 * @since 04.08.2020
 */
class BySelectorNotFoundException(private val selector: BySelector) : IllegalStateException() {
    override fun toString(): String {
        return "Object by selector [${selector.toString().substringAfter("Q").substringBefore("\\E")}] not found"
    }
}
