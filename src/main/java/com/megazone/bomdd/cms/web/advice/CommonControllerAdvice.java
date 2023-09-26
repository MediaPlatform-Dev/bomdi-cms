package com.megazone.bomdd.cms.web.advice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class CommonControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor editor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, editor);
    }

    @ModelAttribute("common")
    public Map<String, Object> common() {
        Map<String, Object> common = new HashMap<>();
        common.put("currentDateTime", LocalDateTime.now());
        return common;
    }
}
