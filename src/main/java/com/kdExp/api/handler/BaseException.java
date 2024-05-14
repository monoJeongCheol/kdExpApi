package com.kdExp.api.handler;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -5119762081943226106L;

    @Getter
    private String userMessage;

    @Getter
    private String systemMessage;

    @Getter
    @Setter
    private String code;

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    | Constructor
    |-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
    public BaseException() {
        super();
    }

    public BaseException(String systemMessage, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(systemMessage, cause, enableSuppression, writableStackTrace);
        this.systemMessage = systemMessage;
    }

    public BaseException(String systemMessage, Throwable cause) {
        super(systemMessage, cause);
        this.systemMessage = systemMessage;
    }

    public BaseException(String systemMessage) {
        super(systemMessage);
        this.systemMessage = systemMessage;
    }

    public BaseException(String systemMessage, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String userMessage) {
        super(systemMessage, cause, enableSuppression, writableStackTrace);
        this.systemMessage = systemMessage;
        this.userMessage = userMessage;
    }

    public String toStringWithStackTrace() {
        return String.format("\n" + "%s" + " - userMessage: %s\n" + " - systemMessage: %s\n" + " - code: %s\n",
                ExceptionUtils.getStackTrace(this), userMessage, systemMessage, code);
    }

}
