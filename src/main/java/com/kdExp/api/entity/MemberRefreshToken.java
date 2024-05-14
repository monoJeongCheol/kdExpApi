package com.kdExp.api.entity;

import java.util.UUID;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("MemberRefreshToken")
public class MemberRefreshToken {
    private UUID uuid;
    private Member member;
    private String refreshToken;
    private int reissueCount = 0;
    
    public MemberRefreshToken(Member member, String refreshToken) {
        this.member = member;
        this.refreshToken = refreshToken;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean validateRefreshToken(String refreshToken) {
        return this.refreshToken.equals(refreshToken);
    }

    public void increaseReissueCount() {
        reissueCount++;
    }
}
