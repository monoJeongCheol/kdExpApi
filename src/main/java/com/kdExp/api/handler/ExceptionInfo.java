package com.kdExp.api.handler;

import lombok.Data;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Data
public class ExceptionInfo {

    private Throwable e;
    private Throwable cause;
    private String userMessage;
    private String systemMessage;
    private boolean forcesOK = false;
    private String code;
    private HttpStatus httpStatus;
    private String templateName;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    public ExceptionInfo(Throwable e) {
        this.e = e;
        this.cause = e.getCause();

        if (e instanceof BizException) {
            BizException be = (BizException) e;
            this.userMessage = be.getUserMessage();
            this.systemMessage = be.getSystemMessage();
            this.forcesOK = be.isForcesOK();
        } else if (e instanceof MethodArgumentNotValidException) {
            Pair<String, String> msg = setValidationError(((MethodArgumentNotValidException) e).getBindingResult());
            this.userMessage = msg.getFirst();
            this.systemMessage = msg.getSecond();
        } else if (e instanceof BindException) {
            Pair<String, String> msg = setValidationError(((BindException) e).getBindingResult());
            this.userMessage = msg.getFirst();
            this.systemMessage = msg.getSecond();
        }
    }

    private Pair<String, String> setValidationError(BindingResult br) {

        FieldError fe = br.getFieldError();
        String errorDefaultMessage = null;
        String errorAttribute = null;
        if(fe != null) {
            errorDefaultMessage = fe.getDefaultMessage();
            errorAttribute = fe.getCodes()[0];
        }else {
            ObjectError oe = br.getGlobalError();
            errorDefaultMessage = oe.getDefaultMessage();
            errorAttribute = oe.getCode();
        }

        String errorMessage = "파라미터 오류";

        if (errorDefaultMessage != null)
            errorMessage = String.format("%s[%s]", errorMessage, errorDefaultMessage);

        return Pair.of(errorMessage, errorAttribute);
    }

    public void printError(){
        log.error("\n" + "{}\n" +
                        " - userMessage: {}\n" +
                        " - systemMessage: {}\n" +
                        " - httpStatus: {}\n" +
                        " - code: {}\n",
                ExceptionUtils.getStackTrace(this.getE()), this.getUserMessage(), this.getSystemMessage(),
                this.getHttpStatus(), this.getCode());
    }

}
