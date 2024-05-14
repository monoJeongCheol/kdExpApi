package com.kdExp.api.service;

import com.kdExp.api.dto.MemberDTO;
import com.kdExp.api.dto.member.request.MemberUpdateDTO;
import com.kdExp.api.dto.member.request.MemberUpdateRequest;
import com.kdExp.api.dto.member.response.MemberDeleteResponse;
import com.kdExp.api.dto.member.response.MemberInfoResponse;
import com.kdExp.api.dto.member.response.MemberUpdateResponse;
import com.kdExp.api.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    
    @Transactional(readOnly = true)	//회원 정보 조회
    public MemberDTO getMemberInfo(MemberDTO memberDto) {
    	
    	MemberDTO resultDto = new MemberDTO();
        resultDto = memberRepository.getMemberInfo(memberDto);
        
        return resultDto;
    }

    @Transactional					// 회원 탈퇴
    public MemberDeleteResponse deleteMember(UUID id) {
        return memberRepository.deleteMember(id);
    }

    @Transactional					// 회원 정보 수정
    public MemberUpdateResponse updateMember(UUID id, MemberUpdateRequest request) {
    	MemberUpdateDTO memberUpdateDTO = new MemberUpdateDTO();
    	memberUpdateDTO.setId(id);
    	memberUpdateDTO.setMemberUpdateRequest(request);
        return memberRepository.updateMember(memberUpdateDTO);
    }
}
