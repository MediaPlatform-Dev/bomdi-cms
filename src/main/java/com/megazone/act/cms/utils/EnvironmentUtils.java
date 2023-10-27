package com.megazone.act.cms.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnvironmentUtils {
    public static final String HOME_PATH = System.getProperty("user.home");
}
