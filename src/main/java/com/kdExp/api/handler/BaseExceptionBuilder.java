package com.kdExp.api.handler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kdExp.api.config.ApplicationContextProvider;
import com.kdExp.api.config.MessageComponent;

public class BaseExceptionBuilder extends Throwable {

	private static final long serialVersionUID = 1L;

	private String userMessage;
    private String userMessageKey;
    private String systemMessage;
    private Throwable cause;
    private boolean enableSuppression = false;
    private boolean writableStackTrace = true;
    private boolean forcesOK = false;
    private String code;
    private Class<?> exceptionType;
    private MessageComponent messageComponent;

    @SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(this.getClass());

    public BaseExceptionBuilder(Class<?> exceptionType) {
        this.exceptionType = exceptionType;
        this.messageComponent = ApplicationContextProvider.getApplicationContext().getBean("messageComponent", MessageComponent.class);
    }

    public BaseExceptionBuilder withUserMessage(String userMessage) {
        this.userMessage = userMessage;
        return this;
    }

    public BaseExceptionBuilder withUserMessageKey(String userMessageKey) {
        this.userMessageKey = userMessageKey;
        return this;
    }

    public BaseExceptionBuilder withSystemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
        return this;
    }

    public BaseExceptionBuilder withCause(Throwable cause) {
        this.cause = cause;
        return this;
    }

    public BaseExceptionBuilder withEnableSuppression(boolean enableSuppression) {
        this.enableSuppression = enableSuppression;
        return this;
    }

    public BaseExceptionBuilder withWritableStackTrace(boolean writableStackTrace) {
        this.writableStackTrace = writableStackTrace;
        return this;
    }

    public BaseExceptionBuilder withForcesOK(boolean forcesOK) {
        this.forcesOK = forcesOK;
        return this;
    }

    public BaseExceptionBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public BaseException build() {
        // userMessage 설정.
        if (StringUtils.isNotBlank(this.userMessageKey)) {
            this.userMessage = messageComponent.getMessage(this.userMessageKey);
        }

        // 유형별 userMessage 설정.
        if (StringUtils.isBlank(this.userMessage)) {
            if (exceptionType.equals(BizException.class))
                this.userMessage = messageComponent.getMessage("common.exception.biz");
        }

        // 유형별 객체 생성.
        BaseException be = null;
        if (exceptionType.equals(BizException.class)) {
            be = new BizException(this.systemMessage,
                    this.cause,
                    this.enableSuppression,
                    this.writableStackTrace,
                    this.userMessage);
            ((BizException) be).setForcesOK(this.forcesOK);
        }
        be.setCode(code);

        return be;
    }





}
