package com.an.maphouse.web.form.annotation;

import com.an.maphouse.web.form.annotation.validate.PhoneValidate;
import com.an.maphouse.web.form.annotation.validate.PhoneValidate;
import com.an.maphouse.web.form.annotation.validate.VerifyCodeTypeValidate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Annan An
 * @date 2020/6/3 16:48
 */
@Target(ElementType.FIELD)//目标是字段
@Retention(RetentionPolicy.RUNTIME) //注解会在class中存在，运行时可通过反射获取
@Inherited
@Constraint(validatedBy = PhoneValidate.class)
public @interface PhoneAnnotation {

    String message() default "手机号格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };
}
