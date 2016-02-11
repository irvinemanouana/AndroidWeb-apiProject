package com.dev.christopher.events.Config;

/**
 * Created by Christopher on 18/01/2016.
 */
public class Configs {

    public static class WEB {

        public static class URL {

            public static final String BASE_HOST        =  "http://192.168.56.1:8080";

            public static final String BASE_OAUTH       = BASE_HOST + "/oauth";

            //Create account
            public static final String URL_API          = BASE_HOST + "/api/user";

            //log User
            public static final String URL_API_LOGIN    = BASE_HOST + "/api/auth/login";

            public static final String URL_API_CAT      = BASE_HOST + "/api/category";
            public static final String OAUTH_TOKEN      = BASE_HOST+"";
        }

        public static class HEADERS {

            public static final String AUTHORIZATION =  "Authorization";
        }
    }

    public static class PREFERENCES {

        public static final String FILE_NAME = "MANAGE_EVENT_ESGI";

        public static class KEYS {

            public static final String CURRENT_USER_TOKEN           = "CURRENT_USER_TOKEN";

            public static final String CURRENT_USER_REFRESH_TOKEN   = "CURRENT_USER_REFRESH_TOKEN";

            public static final String ACCOUNT_USERNAME             = "ACCOUNT_USERNAME";



        }
    }

}
