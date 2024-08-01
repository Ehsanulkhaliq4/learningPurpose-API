package com.exam.examserver.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "temp_save_otp_for_verify")
@Data
public class SaveOtp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "verify_user_otp")
    private int otp;
    @Column(name = "verify_user_email")
    private String email;
}
