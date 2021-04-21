package com.jxz.po;

/**
 * @Project: blog
 * @Package: com.jxz.po
 * @ClassName: TokenInfo
 * @Description: TODO
 * @Author: jiangxinze
 * @Date: 2021/4/19
 * @Version 1.0
 */
public class TokenInfo {
    private String adminId;
    private String token;

    public TokenInfo() {
    }

    public TokenInfo(String adminId, String token) {
        this.adminId = adminId;
        this.token = token;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "adminId=" + adminId +
                ", token='" + token + '\'' +
                '}';
    }
}
