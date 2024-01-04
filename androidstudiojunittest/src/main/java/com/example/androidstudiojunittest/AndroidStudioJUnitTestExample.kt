package com.example.androidstudiojunittest

import org.junit.Assert.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AndroidStudioJUnitTestExample {
    @org.junit.jupiter.api.Test
    fun testIsEven() {
        Assertions.assertTrue(isEven(2))
        Assertions.assertFalse(isEven(3))
    }
}