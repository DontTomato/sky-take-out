package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

public interface UserService {
    /*
    * 微信登录
    * @param userLoginDTO
    * */
    User wxLogin(UserLoginDTO userLoginDTO);

}