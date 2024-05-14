package com.kdExp.api.dto.sign_up.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

import com.kdExp.api.entity.Member;

public record SignUpResponse(
        @Schema(description = "회원 고유키", example = "c0a80121-7aeb-4b4b-8b7a-9b9b9b9b9b9b")
        UUID uuid,
        @Schema(description = "회원 아이디", example = "hongildong")
        String memId,
        @Schema(description = "회원 이름", example = "홍길동")
        String memName,
        @Schema(description = "회원 나이", example = "20")
        String memAge
) {
    public static SignUpResponse from(Member member) {
        return new SignUpResponse(
                member.getUuid(),
                member.getMemId(),
                member.getMemName(),
                member.getMemAge()
        );
    }
}
