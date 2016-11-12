package ru.arvalon.chucknorrisjokes.logs;

import android.util.Log;

import com.vk.sdk.VKAccessToken;

/**
 * Created by arvalon on 10.11.2016.
 */

public class VKAccessTokenLogCat {

    public static void TokenLogcat(VKAccessToken Token){

        Log.d("happy"," TOKEN: "+Token.accessToken);
        Log.d("happy"," TOKEN email: "+Token.email
                +", TOKEN secret: "+Token.secret
                +", TOKEN userId: "+Token.userId
                +", TOKEN created: "+Token.created
                +", TOKEN isExpired: "+Token.isExpired()
                +", TOKEN expiresIn: "+Token.expiresIn);
    }
}
