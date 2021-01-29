package com.rest.demo.utils.anotation.password

import javax.validation.ConstraintValidator
import com.rest.demo.utils.anotation.password.ValidPassword
import javax.validation.ConstraintValidatorContext
import com.rest.demo.utils.exception.NotValidPasswordException
import org.passay.*
import java.util.*

class PasswordConstraintValidator : ConstraintValidator<ValidPassword?, String?> {
    override fun isValid(password: String?, context: ConstraintValidatorContext): Boolean {
        // Constraint rule set
        val validator = PasswordValidator(
            Arrays.asList( // needs at least 8 characters and at most 100 chars
                LengthRule(8, 100),  // at least one upper-case character
                CharacterRule(EnglishCharacterData.UpperCase, 1),  // at least one lower-case character
                CharacterRule(EnglishCharacterData.LowerCase, 1),  // at least one digit character
                CharacterRule(EnglishCharacterData.Digit, 1),  // at least one symbol (special character)
                CharacterRule(EnglishCharacterData.Special, 1),  // no whitespace
                WhitespaceRule()
            )
        )
        // validating password with rule set
        val result = validator.validate(PasswordData(password))
        if (result.isValid) {
            return true
        }
        // if not valid, set messages
        val messages = validator.getMessages(result)
        val messageTemplate = java.lang.String.join(",", messages)
        throw NotValidPasswordException(messageTemplate)
    }
}