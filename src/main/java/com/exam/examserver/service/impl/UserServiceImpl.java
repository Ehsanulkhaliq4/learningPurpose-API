package com.exam.examserver.service.impl;

import com.exam.examserver.helper.UserFoundException;
import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.examserver.repo.RoleRepository;
import com.exam.examserver.repo.UserRepository;
import com.exam.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    //Creating User
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local=this.userRepository.findByUsername(user.getUsername());
        User email = this.userRepository.findByEmailContaining(user.getEmail());
        if (email!=null && local !=null){
            System.out.println("User is Already there!!!");
            throw new UserFoundException();
        }else {
            //create User
            for (UserRole ur:userRoles){
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }
        return local;
    }
    //getting user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email){
        return this.userRepository.findByEmailContaining(email);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
