package com.kdExp.api.dto.member.response;

import com.kdExp.api.common.MemberType;
import com.kdExp.api.entity.Member;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public record MemberInfoResponse(
        @Schema(description = "회원 고유키", example = "c0a80121-7aeb-4b4b-8b0a-6b1c032f0e4a")
        UUID uuid,
        @Schema(description = "회원 아이디", example = "hongildong")
        String memId,
        @Schema(description = "회원 이름", example = "홍길동")
        String memName,
        @Schema(description = "회원 나이", example = "30")
        String memAge,
        @Schema(description = "회원 레벨", example = "1")
        String memLev,
        @Schema(description = "회원 생성일", example = "2023-05-11 15:00:00")
        LocalDateTime regDt
) {
    public static MemberInfoResponse from(Member member) {
        return new MemberInfoResponse(
                member.getUuid(),
                member.getMemId(),
                member.getMemName(),
                member.getMemAge(),
                member.getMemLev(),
                member.getRegDt()
        );
    }
}
