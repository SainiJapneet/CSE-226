package com.example.recyclerview.UNIT_6.Testing

object UtilObject {
    private val existingUsers = listOf("Rahul","Rohan")

    fun validRegistrationInput(userName: String, password: String, confirmPassword: String): Boolean{

        if(userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            return false
        }

        if(userName in existingUsers){
             return false
        }

        if(password != confirmPassword){
            return false
        }

        if(password.count(){it.isDigit()} < 2){
            return false
        }

        return true
    }
}