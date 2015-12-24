package com.sysmobil.sortudao.app.util;

/**
 * Created by peter on 20/12/15.
 */
public class Session {

    boolean active;
    String token;

    public boolean isActive() {
        return active;
    }

    public Session() {
        this.active = false;
        this.token = null;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
