package com.example.ahmadqosam.footballmatchschedule.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsKtTest {

    @Test
    fun testIsNotEmptyOrNull() {
        val arg1 = ""
        assertEquals(false, isNotEmptyOrNull(arg1))
    }
}