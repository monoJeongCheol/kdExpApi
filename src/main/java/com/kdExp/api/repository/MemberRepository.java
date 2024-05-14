package com.kdExp.api.repository;

import com.kdExp.api.dto.MemberDTO;
import com.kdExp.api.dto.member.request.MemberUpdateDTO;
import com.kdExp.api.dto.member.response.MemberDeleteResponse;
import com.kdExp.api.dto.member.response.MemberInfoResponse;
import com.kdExp.api.dto.member.response.MemberUpdateResponse;
import com.kdExp.api.dto.sign_in.request.SignInRequest;
import com.kdExp.api.entity.Member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Mapper
@Repository
public interface MemberRepository {
	//회원 정보 조회
	MemberDTO getMemberInfo(MemberDTO memberDTO);
	// 회원 탈퇴
	MemberDeleteResponse deleteMember(UUID id);
	// 회원 정보 수정
	MemberUpdateResponse updateMember(MemberUpdateDTO memberUpdateDTO);
	// 회원 리스트
	List<MemberDTO> selectMemberInfo();
	// 관리자 리스트
	List<MemberInfoResponse> selectAdminInfo();
	// 회원 가입
	int insertMemberInfo(MemberDTO memberDto);
	// 로그인
	Member signInAccount(SignInRequest request);
	void flush();
}