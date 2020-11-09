package com.an.maphouse.web.form.annotation.validate;

import com.an.maphouse.property.LimitsProperty;
import com.an.maphouse.web.form.annotation.PhoneAnnotation;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Annan An
 * @date 2020/6/3 16:49
 */
public class PhoneValidate implements ConstraintValidator<PhoneAnnotation, String> {
    @Resource
    private LimitsProperty limitsProperty;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 手机号正则校验
        String regex = limitsProperty.getPhoneRegex();
        return value != null && value.matches(regex);
    }
}
