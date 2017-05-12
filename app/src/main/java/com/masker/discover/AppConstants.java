package com.masker.discover;

/**
 * CreatedBy: masker
 * Date: 2017/5/11
 * Description: some constants
 */


public class AppConstants {
    public static final String BASE_URL = "https://api.unsplash.com";
    public static final String APP_ID = "e77b81b3f11fc632130ab341c1be232ab32d5f26a3c3076c4982a98d970627b2";
    public static final String APP_SECRET = "ec5252b84c484e2d49f31449bd4d0743860ba16104199c0d41a7897803f5c240";
    public static final String REDIRECT_URL = "http://shiguangyin.xyz/discover";
    public static final String GRANT_TYPE = "authorization_code";


    public static final String OAUTH_URL = "https://unsplash.com/oauth/authorize?"
            +"client_id="+APP_ID
            +"&redirect_uri="+REDIRECT_URL
            +"&response_type=code"
            +"&scope=public+read_user+write_user+read_photos+write_photos"
            +"+write_likes+write_followers+read_collections+write_collections";

    public static final String TAG_SUFFIX = "?dpr=2&auto=compress,format&crop=entropy&fit=crop&w=376&h=251&q=80&cs=tinysrgb";

}
