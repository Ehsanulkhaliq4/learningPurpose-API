package com.exam.examserver.service.ForgotPassword;

import com.exam.examserver.model.SaveOtp;

import java.util.List;

public interface SaveOtpService {

    SaveOtp saveOtpInDb(String email , int otp);

    Integer getOtpFromDb(int otp);

    List<SaveOtp> getAll();

    void deleteAll();
}
