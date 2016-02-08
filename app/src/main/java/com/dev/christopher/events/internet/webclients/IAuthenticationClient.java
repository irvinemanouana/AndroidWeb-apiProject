package com.dev.christopher.events.internet.webclients;

import com.dev.christopher.events.internet.authentication.utils.GrantType;
import com.dev.christopher.events.internet.json_dto.receiver.AccessTokenInDto;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by nicolas on 07/02/2016.
 */
public interface IAuthenticationClient {

    @FormUrlEncoded
    @POST("/token")
    void asyncGetToken(
            @Field("grant_type") GrantType grantType,
            @Field("username") String username,
            @Field("password") String password,
            Callback<AccessTokenInDto> callback);

    @FormUrlEncoded
    @POST("/token")
    AccessTokenInDto syncGetToken(
            @Field("grant_type") GrantType grantType,
            @Field("username") String username,
            @Field("password") String password) throws RetrofitError;

    @FormUrlEncoded
    @POST("/token")
    void asyncRefreshToken(
            @Field("grant_type") GrantType grantType,
            @Field("refresh_token") String refreshToken,
            Callback<AccessTokenInDto> callback);

    @FormUrlEncoded
    @POST("/token")
    AccessTokenInDto syncRefreshToken(
            @Field("grant_type") GrantType grantType,
            @Field("refresh_token") String refreshToken) throws RetrofitError;
}
