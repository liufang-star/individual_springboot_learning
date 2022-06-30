package com.aop.service;


/**
 * <h3>AspectDemo</h3>
 * <p>service</p>
 *
 * @author : 刘芳
 * @date : 2021-10-25
 **/
public interface UserService {

    /**
     * 获取用户信息
     *
     * @param tel
     * @return
     */
    String findUserName(String tel);

}
