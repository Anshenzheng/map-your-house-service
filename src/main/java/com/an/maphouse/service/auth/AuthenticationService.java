package com.an.maphouse.service.auth;

import com.an.maphouse.web.dto.AuthenticationDTO;
import com.an.maphouse.web.dto.AuthenticationDTO;

import java.util.Optional;

/**
 * 认证服务
 * @author Annan An
 * @date 2020/5/11 11:46
 */
public interface AuthenticationService {

    AuthenticationDTO loginByUsername(String username, String password);

    AuthenticationDTO loginByPhone(String phone, String password);

    AuthenticationDTO noPassLogin(String phone);

    void logout(String token);
}
