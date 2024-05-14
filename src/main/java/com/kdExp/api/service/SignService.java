package com.kdExp.api.service;

import com.kdExp.api.dto.MemberDTO;
import com.kdExp.api.dto.sign_in.request.SignInRequest;
import com.kdExp.api.dto.sign_in.response.SignInResponse;
import com.kdExp.api.entity.Member;
import com.kdExp.api.handler.BizException;
import com.kdExp.api.repository.MemberRepository;
import com.kdExp.api.security.TokenProvider;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SignService {
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder encoder;
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public MemberDTO registMember(MemberDTO memberDto) {
    	MemberDTO responseMemDto = new MemberDTO();
    	memberDto.setMemPwd(encoder.encode(memberDto.getMemPwd()));
    	log.info(memberDto.toString());
    	memberRepository.insertMemberInfo(memberDto);
    	responseMemDto = memberRepository.getMemberInfo(memberDto);	// 위 insert후 시퀀스값으로 조회함
    	
        return responseMemDto;
    }
    
    @Transactional(readOnly = true)
    public SignInResponse signIn(SignInRequest request) {
    	try {
    		SignInResponse response = new SignInResponse();
    		
    		Member member = memberRepository.signInAccount(request);
	    	if(encoder.matches(request.getMemPwd(), member.getMemPwd())) {
	    		log.info(">>>>>>>>>>>>>>>>>> 일치 <<<<<<<<<<<<<<<<<<");
	    		String accessToken = tokenProvider.createAccessToken(String.format("%s:%s", member.getMemId(), member.getMemLev()));
	            String refreshToken = tokenProvider.createRefreshToken();
	    		response.setMemName(member.getMemName());
	    		response.setMemLev(member.getMemLev());
	    		response.setAccessToken(accessToken);
	    		response.setRefreshToken(refreshToken);
	    		
	    		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	    		String secretString = Encoders.BASE64.encode(key.getEncoded());
	    		log.info("secretString ==="+secretString);
	    	}else {
	    		log.info(">>>>>>>>>>>>>>>>>> 불일치 <<<<<<<<<<<<<<<<<<");
	    	}
	        
	        return response;
    	} catch (Exception e) {
			throw BizException.withUserMessage("아이디 또는 비밀번호를 확인해주세요.",
					   String.format("SignService.signIn : %s", e.getMessage())).build();
    	}
    }
}
