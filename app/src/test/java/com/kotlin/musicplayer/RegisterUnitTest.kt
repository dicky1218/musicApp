package com.kotlin.musicplayer

import junit.framework.Assert.assertEquals
import org.junit.Test

class RegisterUnitTest {
    @Test
    fun `login with correct login and password`() {
        val objectUnderTest = RegisterActivity()
        val result = objectUnderTest.register("test2@gmail.com", "P@ssw0rd1", "P@ssw0rd")
        assertEquals("Password is not matching", result)

    }
}