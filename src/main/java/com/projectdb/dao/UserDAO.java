package com.projectdb.dao;

import com.projectdb.domain.User;
import com.projectdb.shared.AbstractCRUD;

public interface UserDAO extends AbstractCRUD<User> {
    User readUserByEmail(String email);
}
