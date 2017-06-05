package com.masker.discover;

/**
 * CreatedBy: masker
 * Date: 2017/5/11
 * Description: some constants
 */


public class AppConstants {
    public static final String BASE_URL = "https://api.unsplash.com";
    public static final String APP_ID = "8e0f33ff297a8c195959ba170b12151a9a781b4425425e9aa975017b7180398e";
    public static final String APP_SECRET = "edf81bebe2116487c2b2c602a8db34de6cfd9d4ffac8d2697e693a3c3f4d5e32";

//    public static final String APP_ID = "f664069d7d02f5cd5d6046fb5b47b58e83026ce1dac492678a5c11b1c38f7273";
//    public static final String APP_SECRET = "2800c16d92029c857e8d3b18d327181436fd8e5913260e12643520caf123f058";

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
