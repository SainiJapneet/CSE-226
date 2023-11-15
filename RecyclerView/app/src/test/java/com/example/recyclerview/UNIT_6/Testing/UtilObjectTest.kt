package com.example.recyclerview.UNIT_6.Testing

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UtilObjectTest{

    @Test
    fun `empty username returns false`(){
        val result = UtilObject.validRegistrationInput(
            "",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `username and correctly repeated password returns true`(){
        val result = UtilObject.validRegistrationInput(
            "Rahul",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `username already taken return false`(){
        val result = UtilObject.validRegistrationInput(
            "Rohan",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `incorrect confirm password returns false`(){
        val result = UtilObject.validRegistrationInput(
            "Rahul",
            "123",
            "1234"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `less than two digit password returns false`(){
        val result = UtilObject.validRegistrationInput(
            "Nisha",
            "1abc",
            "1abc"
        )
        assertThat(result).isFalse()
    }

}