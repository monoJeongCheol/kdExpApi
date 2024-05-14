package com.kdExp.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component("messageComponent")
@RequiredArgsConstructor
public class MessageComponent {

    private final MessageSource messageSource;

    public String getMessage(String code){
        return messageSource.getMessage(code, null, null, LocaleContextHolder.getLocale());
    }

    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, null, LocaleContextHolder.getLocale());
    }

}
