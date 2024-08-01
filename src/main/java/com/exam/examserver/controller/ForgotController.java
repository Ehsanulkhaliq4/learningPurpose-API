package com.exam.examserver.controller;

import com.exam.examserver.model.User;
import com.exam.examserver.repo.UserRepository;
import com.exam.examserver.service.ForgotPassword.EmailService;
import com.exam.examserver.service.ForgotPassword.SaveOtpService;
import com.exam.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ForgotController {

    Random random = new Random(1000);

    @Autowired
    private EmailService service;

    @Autowired
    private SaveOtpService otpService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repository;

    @PostMapping("/get-otp")
    public ResponseEntity<?> sendOtpToEmail(@RequestBody String email){
        //generating otp of 4 digit
        int otp = random.nextInt(9999);
        //code send otp to email
        String subject = "OTP From Learning Purpose Educational Complex";
        String message = "<div style='border:1px solid #e2e2e2; padding:20px'>"+
                "<h1 style='color:back'> OTP = "+"<a style='text-decoration:none'>"+otp+"</a>"+"</h1>"+
                "</div>";
        String to = email;
        this.service.sendEmailWithHtmlTags(to,subject,message);
        ResponseEntity<?> entity = this.saveOtp(email, otp);
        return ResponseEntity.ok(entity);
    }
    public ResponseEntity<?> saveOtp(@RequestBody String email , int otp){
        return ResponseEntity.ok(this.otpService.saveOtpInDb(email, otp));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllData(){
        return ResponseEntity.ok(this.otpService.getAll());
    }
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> request) {
        String password = request.get("password");
        String email = request.get("email");

        User user = repository.findByEmailContaining(email);
        if (user != null && user.getEmail().equals(email)) {
            user.setPassword(this.encoder.encode(password));
            User savedUser = this.repository.save(user);
            return ResponseEntity.ok(savedUser);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/")
    public void deleteAllData(){
        this.otpService.deleteAll();
    }


}
