package com.kdExp.api.dto.sign_in.request;

import org.apache.ibatis.type.Alias;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Alias("SignInRequest")
public class SignInRequest {
        @Schema(description = "회원 아이디", example = "hongildong")
        private String memId;
        @Schema(description = "회원 비밀번호", example = "1234")
        private String memPwd;
        @Schema(description = "회원 인코딩비밀번호", example = "$2a$10$8.HdEhU4vdYCxtsNoQZWkeB4u6il1m2oWrutwpx9VH2Qkr0oXTViS", hidden = true)
        private String encodeMemPwd;
}
