package com.kdExp.api.dto.sign_in.response;

import org.apache.ibatis.type.Alias;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Alias("SignInResponse")
public class SignInResponse{
        @Schema(description = "회원 이름", example = "홍길동")
        String memName;
        @Schema(description = "회원 레벨", example = "1")
        String memLev;
        String accessToken;
        String refreshToken;
}
