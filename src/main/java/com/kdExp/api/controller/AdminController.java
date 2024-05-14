package com.kdExp.api.controller;

import com.kdExp.api.dto.ApiResponse;
import com.kdExp.api.dto.MemberDTO;
import com.kdExp.api.dto.member.response.MemberInfoResponse;
import com.kdExp.api.security.AdminAuthorize;
import com.kdExp.api.service.AdminService;
import com.kdExp.api.swagger.ApiResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "관리자용 API")
@RequiredArgsConstructor
/* @AdminAuthorize */
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Operation(summary = "회원 목록 조회")
    @GetMapping("/members")
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        return ResponseEntity.ok(adminService.getMembers());
    }

    @Operation(summary = "관리자 목록 조회")
    @GetMapping("/admins")
    public ResponseEntity<List<MemberInfoResponse>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAdmins());
    }
}
