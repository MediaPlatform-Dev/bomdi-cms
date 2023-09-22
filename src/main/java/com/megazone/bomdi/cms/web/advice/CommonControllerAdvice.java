package com.megazone.bomdi.cms.web.advice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CommonControllerAdvice {

    @ModelAttribute("common")
    public Map<String, Object> common() {
        Map<String, Object> common = new HashMap<>();
        common.put("currentDateTime", LocalDateTime.now());
        return common;
    }
}
