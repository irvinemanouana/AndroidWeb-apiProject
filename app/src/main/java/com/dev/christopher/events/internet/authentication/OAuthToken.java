package com.dev.christopher.events.internet.authentication;

import com.dev.christopher.events.internet.json_dto.receiver.AccessTokenInDto;

/**
 * Created by nicolas on 07/02/2016.
 */
public class OAuthToken {

    private String mAccessToken;
    private Long mExpireToken;
    private String mTokenType;
    private String mRefreshToken;

    public OAuthToken(){}

    public OAuthToken(AccessTokenInDto dto){
        setAccessToken(dto.getAccessToken());
        setExpireToken(dto.getExpireToken());
        setTokenType(dto.getTokenType());
        setRefreshToken(dto.getRefreshToken());
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        this.mAccessToken = accessToken;
    }

    public Long getExpireToken() {
        return mExpireToken;
    }

    public void setExpireToken(Long expire) {
        this.mExpireToken = expire;
    }

    public String getTokenType() {
        return mTokenType;
    }

    public void setTokenType(String tokenType) {
        this.mTokenType = tokenType;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.mRefreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "{access_token: \"" + getAccessToken() + "\"," +
                "expires_in: " + getExpireToken()  +  "," +
                "token_type: \"" + getTokenType() + "\"," +
                "refresh_token: \"" + getRefreshToken() + "\"}";
    }
}
