package com.an.maphouse.web.form.annotation.validate;

import com.an.maphouse.validate.code.ValidateCodeTypeEnum;
import com.an.maphouse.web.form.annotation.VerifyCodeTypeAnnotation;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Annan An
 * @date 2020/5/22 11:40
 */
public class VerifyCodeTypeValidate implements ConstraintValidator<VerifyCodeTypeAnnotation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ValidateCodeTypeEnum.fromValue(value).isPresent();
    }
}