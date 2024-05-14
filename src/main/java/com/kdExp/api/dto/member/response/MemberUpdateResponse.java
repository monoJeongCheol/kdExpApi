package com.kdExp.api.dto.member.response;

import com.kdExp.api.entity.Member;

import io.swagger.v3.oas.annotations.media.Schema;

public record MemberUpdateResponse(
        @Schema(description = "회원 정보 수정 성공 여부", example = "true")
        boolean result,
        @Schema(description = "회원 이름", example = "홍길동")
        String memName,
        @Schema(description = "회원 나이", example = "30")
        String memAge
) {
    public static MemberUpdateResponse of(boolean result, Member member) {
        return new MemberUpdateResponse(result, member.getMemName(), member.getMemAge());
    }
}
