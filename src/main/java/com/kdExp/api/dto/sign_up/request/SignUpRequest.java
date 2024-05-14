package com.kdExp.api.dto.sign_up.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignUpRequest(
        @Schema(description = "회원 아이디", example = "hongildong")
        String memId,
        @Schema(description = "회원 비밀번호", example = "1234")
        String memPwd,
        @Schema(description = "회원 이름", example = "홍길동")
        String memName,
        @Schema(description = "회원 나이", example = "20")
        String memAge,
        @Schema(description = "회원 레벨", example = "1")
        String memLev
) {
}
