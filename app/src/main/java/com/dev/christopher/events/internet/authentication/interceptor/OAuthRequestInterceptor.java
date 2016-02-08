package com.dev.christopher.events.internet.authentication.interceptor;

import com.dev.christopher.events.Config.Configs;
import com.dev.christopher.events.internet.authentication.utils.AuthenticationUtils;

import retrofit.RequestInterceptor;

/**
 * Created by nicolas on 08/02/2016.
 */
public class OAuthRequestInterceptor  implements RequestInterceptor {
    @Override
    public void intercept(RequestFacade request) {
        request.addHeader(Configs.WEB.HEADERS.AUTHORIZATION, AuthenticationUtils.getClientAuthentication());
    }
}
