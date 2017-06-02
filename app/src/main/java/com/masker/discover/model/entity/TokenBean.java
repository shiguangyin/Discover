package com.masker.discover.model.entity;

/**
 * CreatedBy: masker
 * Date: 2017/5/11
 * Description:acess token
 */

public class TokenBean {


    /**
     * access_token : 6d5f68265fee83268fc8f369df00f50f6e5fe073a3d3f14ffd39fbfa397e83bc
     * token_type : bearer
     * refresh_token : b26d705ce244888e4c9c32d8e1026c2c829a22fd3561ff0d3318cdbc5505e8b2
     * scope : public read_user write_user read_photos write_photos write_likes write_followers read_collections write_collections
     * created_at : 1494492207
     */

    private String access_token;
    private String token_type;
    private String refresh_token;
    private String scope;
    private long created_at;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }
}
