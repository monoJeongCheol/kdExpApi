package com.kdExp.api.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 결과처리 공통모델
 *  - 기본설정은 성공으로 구성함.
 */
@Getter
@Setter
@Schema(name = "result", description = "처리결과")
public class ApiResult<T> {
	/**
     * 결과코드
     *  - 1은 정상임.
     */
	@Schema(name = "resultCode", description = "결과코드. 1이면 정상처리", example = "1")
	private int resultCode = 1;
	
    /**
     * 결과메세지
     */
	@Schema(name = "resultMsg", description = "결과메시지", example = "success")
	private String resultMsg = "";
	
    /**
     * 결과데이터
     */
	private T resultData;
	
	public ApiResult() {
		this.resultCode = 1;
		this.resultMsg = "success";
	}
	
	public ApiResult(T resultData) {
		this.resultCode = 1;
		this.resultMsg = "success";
		this.resultData = resultData;
	}
	
    /**
     * 생성자
     * @param resultCode
     * @param resultMsg
     * @param resultData
     */
	public ApiResult(int resultCode, String resultMsg, T resultData) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.resultData = resultData;
	}
}
