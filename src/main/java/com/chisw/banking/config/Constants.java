package com.chisw.banking.config;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";

    public static final String APP_NAME = "banking_application";
}
