package com.shirospringboot.service;

import com.shirospringboot.pojo.User;

/**
 * 用户接口实现层
 */
public interface UserService {
    public User queryUserByName(String username);
}
