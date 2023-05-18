package com.nativemobilebits.loginflow.data.rules

object Validator {


    fun validateFirstName(fName:String) :ValidationResult{
        return ValidationResult(
            (!fName.isNullOrEmpty() && fName.length>=2)
        )

    }

    fun validateLastName(lName:String) :ValidationResult{
        return ValidationResult(
            (!lName.isNullOrEmpty() && lName.length>=2)
        )
    }

    fun validateEmail(email:String) :ValidationResult{
        return ValidationResult(
            (!email.isNullOrEmpty())
        )
    }

    fun validatePassword(password:String) :ValidationResult {
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length>=4)
        )
    }

}

data class ValidationResult(
    val status :Boolean = false
)








