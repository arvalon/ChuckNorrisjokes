package ru.arvalon.chucknorrisjokes.vk;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by arvalon on 10.11.2016.
 */

public class VkApplication extends Application {

    public static final String VK_ACCESS_TOKEN = "VK_ACCESS_TOKEN";

    private VKAccessToken access_token;


    @Override
    public void onCreate() {

        //access_token = VKAccessToken.tokenFromSharedPreferences(this, VK_ACCESS_TOKEN);

        VKSdk.initialize(this);

        vkAccessTokenTracker.startTracking();

        //access_token.saveTokenToSharedPreferences(this,VK_ACCESS_TOKEN);

    }

    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {

        @Override
        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            Log.d("happy","OLD TOKEN: "+oldToken.accessToken);
            Log.d("happy","OLD TOKEN email: "+oldToken.email
                    +", OLD TOKEN secret: "+oldToken.secret
                    +", OLD TOKEN userId: "+oldToken.userId
                    +", OLD TOKEN created: "+oldToken.created
                    +", OLD TOKEN isExpired: "+oldToken.isExpired()
                    +", OLD TOKEN expiresIn: "+oldToken.expiresIn);

            Log.d("happy","OLD TOKEN: "+newToken.accessToken);
            Log.d("happy","OLD TOKEN email: "+newToken.email
                    +", OLD TOKEN secret: "+newToken.secret
                    +", OLD TOKEN userId: "+newToken.userId
                    +", OLD TOKEN created: "+newToken.created
                    +", OLD TOKEN isExpired: "+newToken.isExpired()
                    +", OLD TOKEN expiresIn: "+newToken.expiresIn);

            if (newToken==null){
                Log.d("happy","New token is null, VKAccessToken is invalid");
            }else Log.d("happy","VKAccessToken is valid");
        }
    };
}
