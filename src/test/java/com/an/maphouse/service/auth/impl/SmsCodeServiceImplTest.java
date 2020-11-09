package com.an.maphouse.service.auth.impl;

import com.an.maphouse.web.form.SendSmsForm;
import com.an.maphouse.MapHouseApplicationTests;
import com.an.maphouse.validate.code.ValidateCodeTypeEnum;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @author Annan An
 * @date 2020/5/22 15:59
 */
class SmsCodeServiceImplTest extends MapHouseApplicationTests {

    @Resource
    private SmsCodeServiceImpl smsCodeService;

    @Test
    void aliSendSms() {
        smsCodeService.aliSendSms("17879502601", "520");
    }

    @Test
    void sendSms(){
        SendSmsForm sendSmsForm = new SendSmsForm();
        sendSmsForm.setOperationType(ValidateCodeTypeEnum.LOGIN.getValue());
        sendSmsForm.setPhoneNumber("17879502601");
        smsCodeService.sendSms("17879502601", ValidateCodeTypeEnum.LOGIN.getValue());
    }
}