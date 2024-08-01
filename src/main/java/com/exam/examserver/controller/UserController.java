package com.exam.examserver.controller;

import com.exam.examserver.helper.UserFoundException;
import com.exam.examserver.model.Role;
import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.examserver.service.ForgotPassword.EmailService;
import com.exam.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    //Creating User
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        user.setProfile("default.png");
        //going to encode password with BCryptPasswordEncoder
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> roles= new HashSet<>();
        //Setting Role
        Role role=new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");
        //Setting userRole
        UserRole userRole=new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        roles.add(userRole);
        User savedUser = this.userService.createUser(user, roles);
        String message = " <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f4f4f4;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            color: #333;\n" +
                "        }\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 50px auto;\n" +
                "            background-color: #fff;\n" +
                "            padding: 20px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "            border-radius: 8px;\n" +
                "        }\n" +
                "        .header {\n" +
                "            text-align: center;\n" +
                "            border-bottom: 1px solid #ddd;\n" +
                "            padding-bottom: 10px;\n" +
                "        }\n" +
                "        .header h1 {\n" +
                "            margin: 0;\n" +
                "            font-size: 24px;\n" +
                "            color: #333;\n" +
                "        }\n" +
                "        .content {\n" +
                "            padding: 20px 0;\n" +
                "        }\n" +
                "        .content p {\n" +
                "            line-height: 1.6;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            text-align: center;\n" +
                "            border-top: 1px solid #ddd;\n" +
                "            padding-top: 10px;\n" +
                "            font-size: 14px;\n" +
                "            color: #777;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <h1>Welcome to Our Community!</h1>\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <p>Hi there,</p>\n" +
                "            <p>Thank you for signing up! We're excited to have you on board.</p>\n" +
                "            <p>At Learning Purpose, we're dedicated to providing you with the best experience possible. Whether you're here to learn, explore, or connect, we're here to support you every step of the way.</p>\n" +
                "            <p>Stay tuned for updates, and feel free to explore our platform. If you have any questions or need assistance, don't hesitate to reach out to our support team.</p>\n" +
                "            <p>Best regards,<br>Learning Purpose By Virtual Society</p>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>&copy; 2024 Learning Purpose. All rights reserved.</p>\n" +
                "        </div>\n" +
                "    </div>";
        emailService.sendEmailWithHtmlTags(savedUser.getEmail(),

                "Thanks For Sign Up : Email From  Learning-Purpose",message);
        return savedUser;
    }
    //update User

    //Get User by username
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username)
    {
        return this.userService.getUser(username);
    }
    //delete user by userId
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId)
    {
        this.userService.deleteUser(userId);
    }

    //Get ALl users
    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/getUserByEmail")
    public ResponseEntity<User> getUserByEmail(@RequestBody String email){
        return ResponseEntity.ok(this.userService.getUserByEmail(email));
    }
    //User not found exception
    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex){
        return new ResponseEntity<>(ex,HttpStatus.NOT_FOUND);
    }

}
