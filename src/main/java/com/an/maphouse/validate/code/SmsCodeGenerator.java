package com.an.maphouse.validate.code;

import com.an.maphouse.property.ALiYunSmsProperty;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Annan An
 * @date 2020/5/22 13:43
 */
@Component
@Slf4j
public class SmsCodeGenerator {

    @Autowired
    private ALiYunSmsProperty aLiYunSmsProperties;

    public ValidateCode generate2() {
        String code = RandomStringUtils.randomNumeric(aLiYunSmsProperties.getLength());
        int expireIn = aLiYunSmsProperties.getExpireIn();
        int resendInterval = aLiYunSmsProperties.getResendInterval();
        log.info("SMS code: ", code);
        return new ValidateCode(code, expireIn, resendInterval);
    }

    public ValidateCode generate() {
        String code = RandomStringUtils.randomNumeric(6);
        int expireIn = 300;
        int resendInterval = 60;
        log.info("SMS code: ", code);
        return new ValidateCode(code, expireIn, resendInterval);
    }
}
