package com.exam.examserver.service;

import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {

    //create
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
    //get User by username
    public User getUser(String username);
    //delete user by username
    public void deleteUser(Long userId);

    List<User> getAllUsers();
}
