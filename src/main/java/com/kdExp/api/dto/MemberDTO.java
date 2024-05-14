package com.kdExp.api.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Alias("MemberDTO")
public class MemberDTO {
	private int memNo;				// 회원 연번
	private String uuid;			// UUID
	private String memId;			// 회원 ID
	private String memAge;			// 회원 나이
	private String memName;			// 회원 이름
	private String memPwd;			// 회원 비번
	private String memLev;			// 회원 레벨
	private String regId;			// 등록 ID
	private String regDt;			// 등록 일시
	private String modId;			// 수정 ID
	private String modDt;			// 수정 일시
	private String statInfo;		// 상태 정보
}
