package com.kdExp.api.controller;

import com.kdExp.api.dto.ApiResponse;
import com.kdExp.api.dto.MemberDTO;
import com.kdExp.api.dto.sign_in.request.SignInRequest;
import com.kdExp.api.dto.sign_in.response.SignInResponse;
import com.kdExp.api.dto.sign_up.request.SignUpRequest;
import com.kdExp.api.service.AdminService;
import com.kdExp.api.service.SignService;
import com.kdExp.api.swagger.ApiResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 가입 및 로그인")
@RequiredArgsConstructor
@RestController
@RequestMapping("/sign")
/* @CrossOrigin(origins = "*", methods = RequestMethod.GET) */ 
public class SignController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
    private final SignService signService;
    private final AdminService adminService;

    @Operation(summary = "회원 가입")
    @PostMapping("/sign-up")
    public ResponseEntity<MemberDTO> signUp(@RequestBody MemberDTO memberDto) {
    	return ResponseEntity.ok(signService.registMember(memberDto));
    }
//    public ApiResponse signUp(@RequestBody MemberDTO memberDto) {
//    	log.info(memberDto.toString());
//        return ApiResponse.success(signService.registMember(memberDto));
//    }

    
    @Operation(summary = "회원 목록 조회")
    @GetMapping("/members")
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        return ResponseEntity.ok(adminService.getMembers());
    }

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(signService.signIn(request));
    }
}
