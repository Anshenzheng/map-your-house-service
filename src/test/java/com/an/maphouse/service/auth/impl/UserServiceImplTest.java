package com.an.maphouse.service.auth.impl;

import com.an.maphouse.base.UserRoleEnum;
import com.an.maphouse.web.form.UserPhoneRegisterForm;
import com.an.maphouse.MapHouseApplicationTests;
import com.an.maphouse.service.auth.UserService;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import java.util.Collections;

/**
 * @author Annan An
 * @date 2020/5/22 15:44
 */
class UserServiceImplTest extends MapHouseApplicationTests {

    @Resource
    private UserService userService;

    @Test
    void registerUserByPhone() {
        UserPhoneRegisterForm userPhoneRegisterForm = new UserPhoneRegisterForm();
        userPhoneRegisterForm.setPassword("12345678q");
        userPhoneRegisterForm.setPhoneNumber("17879502601");
        userService.registerUserByPhone(userPhoneRegisterForm, Collections.singletonList(UserRoleEnum.ADMIN));
    }
}