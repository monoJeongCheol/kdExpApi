package com.kdExp.api.entity;

import com.kdExp.api.common.MemberType;
import com.kdExp.api.dto.member.request.MemberUpdateRequest;
import com.kdExp.api.dto.sign_up.request.SignUpRequest;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Alias("Member")
public class Member {
    @Column(nullable = false, scale = 20, unique = true)
    private String memId;
    @Column(nullable = false)
    private String memPwd;
    private String memName;
    private String memAge;
    private String memLev;
    @CreationTimestamp
    private LocalDateTime regDt;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    
    public static Member from(SignUpRequest request, PasswordEncoder encoder) {
        return Member.builder()
                .memId(request.memId())
                .memPwd(encoder.encode(request.memPwd()))
                .memName(request.memName())
                .memAge(request.memAge())
                .memLev(request.memLev())
                .build();
    }

    @Builder
    private Member(String memId, String memPwd, String memName, String memAge, String memLev) {
        this.memId = memId;
        this.memPwd = memPwd;
        this.memName = memName;
        this.memAge = memAge;
        this.memLev = memLev;
    }

    public void update(MemberUpdateRequest newMember, PasswordEncoder encoder) {
        this.memPwd = newMember.newMemPwd() == null || newMember.newMemPwd().isBlank()
                ? this.memPwd : encoder.encode(newMember.newMemPwd());
        this.memName = newMember.memName();
        this.memAge = newMember.memPwd();
    }
}
