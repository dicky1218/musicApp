package com.kotlin.musicplayer

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterUnitTest {
    @Test
    fun `register correct user`() {
        val objects = RegisterActivity()
        val result = objects.register("test2@gmail.com", "P@ssw0rd", "P@ssw0rd")
        assertTrue(result)
    }

    @Test
    fun `register incorrect password`() {
        val objects = RegisterActivity()
        val result = objects.register("test2@gmail.com", "P@ssw0rd1", "P@ssw0rd")
        assertFalse(result)
    }

    @Test
    fun `register empty field`() {
        val objects = RegisterActivity()
        val result = objects.register("test2@gmail.com", "", "")
        assertFalse(result)
    }
}