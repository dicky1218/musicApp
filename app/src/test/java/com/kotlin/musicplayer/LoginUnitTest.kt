package com.kotlin.musicplayer

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginUnitTest {
    @Test
    fun `login correct user`() {
        val objects = LoginActivity()
        val result = objects.login("admin@gmail.com", "P@ssw0rd")
        assertTrue(result)
    }

    @Test
    fun `login incorrect user`() {
        val objects = RegisterActivity()
        val result = objects.register("test3@gmail.com", "P@ssw0rd1", "P@ssw0rd")
        assertFalse(result)
    }
}