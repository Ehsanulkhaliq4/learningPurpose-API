package com.exam.examserver.repo;

import com.exam.examserver.model.SaveOtp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaveOtpRepo extends JpaRepository<SaveOtp,Long> {
    Integer findByOtp(int otp);
}
