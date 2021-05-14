package com.example.testapplication


object TryEvaluate {
    operator fun invoke(block: () -> Unit) = eval(block)

    private fun eval(block: () -> Unit, time: Long = 5000) {
        val tryCount = time / 250
        var count = 0
        do {
            try {
                count++
                block.invoke()
                break
            } catch (e: Exception) {
                if (count < tryCount) println("Retry count = [$count]") else throw e
                println("Try again")
                Thread.sleep(250)
            }
        } while (count < tryCount)
    }
}
