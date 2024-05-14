package com.kdExp.api.service;

import com.kdExp.api.dto.MemberDTO;
import com.kdExp.api.dto.member.response.MemberInfoResponse;
import com.kdExp.api.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class AdminService {
	Logger log = LoggerFactory.getLogger(this.getClass());
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberDTO> getMembers() {
    	List<MemberDTO> list = memberRepository.selectMemberInfo();
    	log.info("list >>>>>>>>>>>>>>>>> "+list);
        return list;
    }

    @Transactional(readOnly = true)
    public List<MemberInfoResponse> getAdmins() {
        return memberRepository.selectAdminInfo();
    }
}
