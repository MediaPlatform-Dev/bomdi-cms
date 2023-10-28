package com.megazone.act.cms.web.advice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.megazone.act.cms.web.ui.dto.CommonTypes;
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

    @ModelAttribute("commonTime")
    public Map<String, Object> commonTime() {
        Map<String, Object> common = new HashMap<>();
        common.put("currentDateTime", LocalDateTime.now());
        return common;
    }

    @ModelAttribute("commonTypes")
    public CommonTypes commonTypes() {
        return CommonTypes.TYPES;
    }
}
