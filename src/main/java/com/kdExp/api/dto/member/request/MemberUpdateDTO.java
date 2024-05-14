package com.kdExp.api.dto.member.request;

import java.util.UUID;

import org.apache.ibatis.type.Alias;
import lombok.Data;

@Data
@Alias("MemberUpdateDTO")
public class MemberUpdateDTO {
	private UUID id;
	
	private MemberUpdateRequest memberUpdateRequest;
	
}

