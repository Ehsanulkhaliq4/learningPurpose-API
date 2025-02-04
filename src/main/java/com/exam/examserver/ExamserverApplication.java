package com.exam.examserver;

import com.exam.examserver.model.Role;
import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Starting Code");
//        User user = new User();
//        user.setFirstName("Ehsan");
//        user.setLastName("ul khaliq");
//        user.setUsername("Ehsanulkhaliq");
//        user.setPassword(this.bCryptPasswordEncoder.encode("Eh202036"));
//        user.setPhone("03487350330");
//        user.setEmail("ehsanmalik@gmail");
//        user.setProfile("default.png");
//        Role role1=new Role();
//        role1.setRoleId(44L);
//        role1.setRoleName("ADMIN");
//        Set<UserRole> userRoleSet = new HashSet<>();
//        UserRole userRole= new UserRole();
//        userRole.setRole(role1);
//        userRole.setUser(user);
//        userRoleSet.add(userRole);
//        User user1 = userService.createUser(user, userRoleSet);
//        System.out.println(user1.getUsername());
    }
}
