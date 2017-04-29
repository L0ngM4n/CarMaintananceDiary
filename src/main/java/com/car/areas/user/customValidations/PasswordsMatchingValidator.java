package com.car.areas.user.customValidations;


import com.car.areas.user.models.bindingModels.RegistrationModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 03/04/2017
 */
public class PasswordsMatchingValidator implements ConstraintValidator<ArePasswordsMatching, Object> {
   public void initialize(ArePasswordsMatching constraint) {
   }

   public boolean isValid(Object obj, ConstraintValidatorContext context) {

      if (obj instanceof RegistrationModel) {
         if(((RegistrationModel) obj).getPassword().equals(((RegistrationModel) obj).getConfirmPassword())) {
            return true;
         }
      }
      return false;
   }

}
