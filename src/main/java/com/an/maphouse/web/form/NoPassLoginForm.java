package com.an.maphouse.web.form;

import com.an.maphouse.web.form.annotation.PhoneAnnotation;
import com.an.maphouse.web.form.annotation.PasswordStrongAnnotation;
import com.an.maphouse.web.form.annotation.PhoneAnnotation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Annan An
 * @date 2020/6/3 13:38
 */
@Data
public class NoPassLoginForm {

    @ApiModelProperty(value = "手机号", required = true)
    @NotNull(message = "手机号不能为空")
    @PhoneAnnotation()
    private String phoneNumber;

    @NotNull(message = "验证码不能为空")
    @ApiModelProperty(value = "手机验证码", required = true)
    private String verifyCode;
}
