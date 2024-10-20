package org.likelion.likelioncrudexcepvalid.member.application.Validator;

import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintValidator;
import org.likelion.likelioncrudexcepvalid.member.domain.Part;

public class EnumValidator implements ConstraintValidator<EnumValid, Part> {
    @Override
    public boolean isValid(Part part, ConstraintValidatorContext context) {
        if (part == null) {
            return true;
        }
        return part == Part.WEB || part == Part.SERVER;
    }
}
