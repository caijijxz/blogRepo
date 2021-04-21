package com.jxz.service;

import com.jxz.po.User;

public interface UserService {

    User checkUser(String username, String password);

}
