package com.example.testing

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * | Method Name               | Description                                                                 |
 * |---------------------------|-----------------------------------------------------------------------------|
 * | `setup()`                 | Initializes the `Calculator` object before each test.                       |
 * | `addition_isCorrect()`    | Tests if the `add` method in `Calculator` returns the correct sum.          |
 * | `subtraction_isCorrect()` | Tests if the `subtract` method in `Calculator` returns the correct result.  |
 * | `multiplication_isCorrect()` | Tests if the `multiply` method in `Calculator` returns the correct product. |
 * | `division_isCorrect()`    | Tests if the `divide` method in `Calculator` returns the correct quotient.  |
 * | `division_byZero_throwsException()` | Tests if the `divide` method in `Calculator` throws an exception when dividing by zero. |
 */

class Calculator {
    fun add(a: Int, b: Int) = a + b
    fun subtract(a: Int, b: Int) = a - b
    fun multiply(a: Int, b: Int) = a * b
    fun divide(a: Int, b: Int) = if (b != 0) a / b else throw IllegalArgumentException("Cannot divide by zero")
}


class CalculatorTest {
    private lateinit var calculator: Calculator

    @Before
    fun setup() {
        calculator = Calculator()
    }

    @Test
    fun addition_isCorrect() {
        val result = calculator.add(2, 2)
        assertEquals(4, result)
    }

    @Test
    fun subtraction_isCorrect() {
        val result = calculator.subtract(2, 2)
        assertEquals(0, result)
    }

    @Test
    fun multiplication_isCorrect() {
        val result = calculator.multiply(2, 2)
        assertEquals(4, result)
    }

    @Test
    fun division_isCorrect() {
        val result = calculator.divide(4, 2)
        assertEquals(2, result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun division_byZero_throwsException() {
        calculator.divide(4, 0)
    }
}