package com.starcast.account.repository;

import com.starcast.account.model.User;

public interface UserRepositoryCustom {
    User findByMobileNumber(Long mobilenumber);
    User findByEmail(String email);
}
