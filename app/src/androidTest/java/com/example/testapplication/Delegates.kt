@file:JvmName("CommonUtils")

package com.example.testapplication

fun <T> lazyUnsafe(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)
