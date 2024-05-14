package com.kdExp.api.dto.member.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record MemberUpdateRequest(
        @Schema(description = "회원 id", example = "abcd")
        String memId,		
        @Schema(description = "회원 비밀번호", example = "1234")
        String memPwd,
        @Schema(description = "회원 새 비밀번호", example = "1234")
        String newMemPwd,
        @Schema(description = "회원 이름", example = "홍길동")
        String memName,
        @Schema(description = "회원 나이", example = "20")
        String memAge
) {
}
