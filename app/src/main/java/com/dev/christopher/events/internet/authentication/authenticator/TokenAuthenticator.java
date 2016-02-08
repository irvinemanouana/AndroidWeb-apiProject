package com.dev.christopher.events.internet.authentication.authenticator;

import com.dev.christopher.events.Config.Configs;
import com.dev.christopher.events.internet.authentication.OAuthToken;
import com.dev.christopher.events.managers.UserManager;

import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.Proxy;

/**
 * Created by nicolas on 08/02/2016.
 */
public class TokenAuthenticator implements Authenticator {

    @Override
    public Request authenticate(Proxy proxy, Response response) throws IOException {
        final int statusCode = response.code();

        if (statusCode == 401) {
            OAuthToken token = UserManager.getInstance().syncRefreshTokenAPI();

            if (token != null){
                return response.request().newBuilder()
                        .removeHeader(Configs.WEB.HEADERS.AUTHORIZATION)
                        .header(Configs.WEB.HEADERS.AUTHORIZATION, String.format("Bearer %s", token.getAccessToken()))
                        .build();
            }
        }

        return response.request();
    }

    @Override
    public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
        return null;
    }
}
