package com.kdExp.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.kdExp.api.entity.MemberRefreshToken;

@Mapper
@Repository
public interface MemberRefreshTokenRepository {

	Optional<MemberRefreshToken> findByMemberIdAndReissueCountLessThan(UUID id, long count);
}
