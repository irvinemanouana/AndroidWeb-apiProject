package com.dev.christopher.events.internet.json_dto.receiver;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nicolas on 08/02/2016.
 */
public class AccessTokenInDto {
    @SerializedName("access_token") private String mAccessToken;

    @SerializedName("expires_in") private Long mExpireToken;

    @SerializedName("token_type") private String mTokenType;

    @SerializedName("refresh_token") private String mRefreshToken;

    public String getAccessToken() {
        return mAccessToken;
    }

    public Long getExpireToken() {
        return mExpireToken;
    }

    public String getTokenType() {
        return mTokenType;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    @Override
    public String toString() {
        return "{access_token: \"" + getAccessToken() + "\"," +
                "expires_in: " + getExpireToken()  +  "," +
                "token_type: \"" + getTokenType() + "\"," +
                "refresh_token: \"" + getRefreshToken() + "\"}";
    }
}
