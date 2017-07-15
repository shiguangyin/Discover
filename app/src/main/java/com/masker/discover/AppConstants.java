package com.masker.discover;

/**
 * CreatedBy: masker
 * Date: 2017/5/11
 * Description: some constants
 */


public class AppConstants {
    public static final String BASE_URL = "https://api.unsplash.com";
    public static final String APP_ID = "a86c44ac40a9f4644a69606c882c3957c4119bfba81632b4742dbc843509142a";
    public static final String APP_SECRET = "627c13ac551c16f9878aab7bb4740e932d83da9ec437811dade52f05ab632ae6";
    

    public static final String REDIRECT_URL = "discover://oauth.callback";
    public static final String OATHU_HOST = "oauth.callback";
    public static final String GRANT_TYPE = "authorization_code";


    public static final String OAUTH_URL = "https://unsplash.com/oauth/authorize?"
            +"client_id="+APP_ID
            +"&redirect_uri="+REDIRECT_URL
            +"&response_type=code"
            +"&scope=public+read_user+write_user+read_photos+write_photos"
            +"+write_likes+write_followers+read_collections+write_collections";

    public static final String JOIN_URL = "https://unsplash.com/join";

    public static final String TAG_SUFFIX = "?dpr=2&auto=compress,format&crop=entropy&fit=crop&w=376&h=251&q=80&cs=tinysrgb";

}
