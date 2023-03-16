package com.a604.cake4u.seller.entity;

import com.a604.cake4u.enums.EGender;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Seller {
    @Id
    private Long id;
    //  기본이 최대 255자
    @Column(unique = false, nullable = false)
    private String email;

    @Column(nullable = false, length = 30)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EGender gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String roadAddress;

    @Column(nullable = false)
    private String detailedAddress;

    @Column(nullable = false, length = 10)
    private String dongCode;

    @Column(nullable = false, length = 40)
    private String buildingName;

    @Column(nullable = false, length = 12)
    private String phoneNumber;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, unique = true, length = 10)
    private String businessNumber;

    @Column(nullable = false)
    private String businessLocation;

    @Column(nullable = false, length = 60)
    private String businessName;

    @Column(nullable = false, length = 1000)
    private String businessDescription;

    //  문의 계정
    @Column(nullable = false, length = 1024)
    private String contact;

    @Column(nullable = false, length = 100)
    private String account;

    @Column(nullable = true, length = 300)
    private String refreshToken;
}