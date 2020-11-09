package com.an.maphouse.test;

import com.an.maphouse.web.form.UserBasicInfoForm;
import com.an.maphouse.MapHouseApplicationTests;
import com.an.maphouse.entity.User;
import com.an.maphouse.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import javax.annotation.Resource;

/**
 * @author Annan An
 * @date 2020/8/9 17:43
 */
public class ModelMapperTest extends MapHouseApplicationTests {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private UserRepository userRepository;

    @Test
    void testMapper(){
        User user = userRepository.findById(2L).orElse(null);
        System.out.println(user);
        UserBasicInfoForm userForm = new UserBasicInfoForm();
        userForm.setAvatar("abcd");
        userForm.setIntroduction("如此简单");
        modelMapper.map(userForm, user);
        System.out.println(user);
    }
}
