package com.exam.examserver.service.ForgotPassword.impl;

import com.exam.examserver.model.SaveOtp;
import com.exam.examserver.repo.SaveOtpRepo;
import com.exam.examserver.service.ForgotPassword.SaveOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveOtpImpl implements SaveOtpService {
    @Autowired
    private SaveOtpRepo otpRepo;

    @Override
    public SaveOtp saveOtpInDb(String email , int otp){
        SaveOtp otp1 = new SaveOtp();
        otp1.setOtp(otp);
        otp1.setEmail(email);
        return this.otpRepo.save(otp1);
    }
    @Override
    public Integer getOtpFromDb(int otp){
        return this.otpRepo.findByOtp(otp);
    }

    @Override
    public List<SaveOtp> getAll(){
        return this.otpRepo.findAll();
    }

    @Override
    public void deleteAll(){
        this.otpRepo.deleteAll();
    }
}
