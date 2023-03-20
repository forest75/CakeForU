package com.a604.cake4u.buyer.entity;

import com.a604.cake4u.enums.EGender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Buyer {
    @SequenceGenerator(
            name="BUYER_SEQ_GEN",
            sequenceName = "BUYER_SEQ",
            initialValue = 100,
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUYER_SEQ_GEN")
    private Long id;

    //  기본이 최대 255자
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false, length = 20)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EGender gender;

    @Column
    private LocalDate birthDate;

    @Column(length = 12)
    private String phoneNumber;

    //  최초 가입 시에는 RefreshToken 없는 상태
    @Column(nullable = true, length = 300)
    private String refreshToken;
}