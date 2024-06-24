package com.projectnmt.dutyalram.service;

import com.projectnmt.dutyalram.entity.User;
import com.projectnmt.dutyalram.repository.UserMapper;
import com.projectnmt.dutyalram.security.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrincipalService {
    @Autowired
    UserMapper userMapper;
    public PrincipalUser getPrincipal() {
        User user = userMapper.findUserTest();
        return user.toPrincipalUser();
    }
}
