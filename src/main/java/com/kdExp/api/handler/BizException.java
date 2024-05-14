package com.kdExp.api.handler;

import lombok.Getter;
import lombok.Setter;

// 내부 로직 오류
public class BizException extends BaseException {

    private static final long serialVersionUID = 3584878617244811296L;

    @Getter
    @Setter
    private boolean forcesOK = false;

    public BizException(String systemMessage, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String userMessage) {
        super(systemMessage, cause, enableSuppression, writableStackTrace, userMessage);
    }

    public static BaseExceptionBuilder withUserMessage(String userMessage, String systemMessage) {
        BaseExceptionBuilder builder = new BaseExceptionBuilder(BizException.class);
        builder.withUserMessage(userMessage);
        builder.withSystemMessage(systemMessage);
        return builder;
    }

    public static BaseExceptionBuilder withUserMessage(String userMessage, String systemMessage, boolean forcesOK) {
        BaseExceptionBuilder builder = new BaseExceptionBuilder(BizException.class);
        builder.withUserMessage(userMessage);
        builder.withSystemMessage(systemMessage);
        builder.withForcesOK(forcesOK);
        return builder;
    }

    public static BaseExceptionBuilder withUserMessageKey(String userMessageKey, String systemMessage) {
        BaseExceptionBuilder builder = new BaseExceptionBuilder(BizException.class);
        builder.withUserMessageKey(userMessageKey);
        builder.withSystemMessage(systemMessage);
        return builder;
    }

    public static BaseExceptionBuilder withUserMessageKey(String userMessageKey, String systemMessage, boolean forcesOK) {
        BaseExceptionBuilder builder = new BaseExceptionBuilder(BizException.class);
        builder.withUserMessageKey(userMessageKey);
        builder.withSystemMessage(systemMessage);
        builder.withForcesOK(forcesOK);
        return builder;
    }
}
