package com.projectdb.service;

import com.projectdb.domain.User;
import com.projectdb.shared.AbstractCRUD;

public interface UserService extends AbstractCRUD<User> {
    User readUserByEmail(String email);
}
