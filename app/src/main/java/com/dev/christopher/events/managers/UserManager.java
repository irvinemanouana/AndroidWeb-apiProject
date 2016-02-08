package com.dev.christopher.events.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.dev.christopher.events.Config.Configs;
import com.dev.christopher.events.app.EventApp;
import com.dev.christopher.events.internet.authentication.OAuthToken;
import com.dev.christopher.events.internet.authentication.interceptor.OAuthRequestInterceptor;
import com.dev.christopher.events.internet.authentication.utils.GrantType;
import com.dev.christopher.events.internet.json_dto.receiver.AccessTokenInDto;
import com.dev.christopher.events.internet.webclients.IAuthenticationClient;
import com.google.gson.Gson;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by nicolas on 07/02/2016.
 */
public class UserManager {

    /*********************************** INTERFACE USER MANAGER ***********************************/
    public interface CallbackOAuthAPI {
        void onSuccess(OAuthToken token);

        void onFailure(RetrofitError error);
    }

    /************************************* VARS USER MANAGER **************************************/
    private SharedPreferences mPreferences;
    private IAuthenticationClient mOAuthService;

    /*********************************** INSTANCE USER MANAGER ************************************/
    private static UserManager INSTANCE = new UserManager();

    private UserManager() {
        mPreferences = EventApp.baseContext
                .getSharedPreferences(Configs.PREFERENCES.FILE_NAME, Context.MODE_PRIVATE);

        mOAuthService = getOAuthRestAPI();
    }

    public static synchronized UserManager getInstance() {
        return INSTANCE;
    }

    /************************************ GETTER USER MANAGER *************************************/
    public OAuthToken getOAuthToken() {

        OAuthToken mOAuthToken = new OAuthToken();
        mOAuthToken.setAccessToken(mPreferences.
                getString(Configs.PREFERENCES.KEYS.CURRENT_USER_TOKEN, null));

        mOAuthToken.setRefreshToken(mPreferences.
                getString(Configs.PREFERENCES.KEYS.CURRENT_USER_REFRESH_TOKEN, null));

        return mOAuthToken;
    }

    /********************************* PRIVATE METHOD USER MANAGER ********************************/
    private void updateUsernameSharedPref(String username) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(Configs.PREFERENCES.KEYS.ACCOUNT_USERNAME, username);
        editor.apply();
    }

    private void updateTokenSharedPref(OAuthToken token) {
        SharedPreferences.Editor editor = mPreferences.edit();

        String accessToken = (token != null) ? token.getAccessToken() : null;
        String refreshToken = (token != null) ? token.getRefreshToken() : null;

        editor.putString(Configs.PREFERENCES.KEYS.CURRENT_USER_TOKEN, accessToken);
        editor.putString(Configs.PREFERENCES.KEYS.CURRENT_USER_REFRESH_TOKEN, refreshToken);

        editor.apply();
    }

    /********************************* PUBLIC METHOD USER MANAGER *********************************/
    public void onUserLogged(String username, OAuthToken token) {
        updateUsernameSharedPref(username);
        updateTokenSharedPref(token);
    }

    public void onDisconnect() {
        updateUsernameSharedPref(null);
        updateTokenSharedPref(null);
    }

    /********************************* REST METHOD AUTHENTICATION *********************************/
    private IAuthenticationClient getOAuthRestAPI() {
        RestAdapter retrofitTaoOauth = new RestAdapter.Builder()
                .setEndpoint(Configs.WEB.URL.BASE_OAUTH)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(new OAuthRequestInterceptor())
                .setConverter(new GsonConverter(new Gson()))
                .build();

        return retrofitTaoOauth.create(IAuthenticationClient.class);
    }

    public void asyncLogin(final String username, String password, final CallbackOAuthAPI callback) {
        mOAuthService.asyncGetToken(GrantType.password, username, password, new Callback<AccessTokenInDto>() {
            @Override
            public void success(AccessTokenInDto accessTokenInDto, Response response) {
                OAuthToken oAuthToken = new OAuthToken(accessTokenInDto);
                onUserLogged(username, oAuthToken);
                callback.onSuccess(oAuthToken);
            }

            @Override
            public void failure(RetrofitError error) {
                callback.onFailure(error);
            }
        });
    }

    public OAuthToken syncRefreshTokenAPI() {
        try {
            AccessTokenInDto dto = mOAuthService.syncRefreshToken(GrantType.refresh_token, getOAuthToken().getRefreshToken());
            if (dto != null) {
                OAuthToken oAuthToken = new OAuthToken(dto);
                updateTokenSharedPref(oAuthToken);
                return oAuthToken;
            } else {
                onDisconnect();
                return null;
            }
        } catch (RetrofitError error) {
            onDisconnect();
            return null;
        }
    }

    public void asyncExecuteRefreshTokenAPI(final CallbackOAuthAPI callback) {
        mOAuthService.asyncRefreshToken(GrantType.refresh_token, getOAuthToken().getRefreshToken(), new Callback<AccessTokenInDto>() {
            @Override
            public void success(AccessTokenInDto accessTokenInDto, Response response) {
                OAuthToken oAuthToken = new OAuthToken(accessTokenInDto);
                updateTokenSharedPref(oAuthToken);
                callback.onSuccess(oAuthToken);
            }

            @Override
            public void failure(RetrofitError error) {
                onDisconnect();
                callback.onFailure(error);
            }
        });
    }

    public AccessTokenInDto syncExecuteLoginAPI(String username, String password) {
        return mOAuthService.syncGetToken(GrantType.password, username, password);
    }
}
