package com.uic.po;

/**
 * 系统用户类，用于用户认证
 * id 序列号
 * user_id 登录用id
 * user_password 登录用密码
 * user_role_id 用户角色id
 * flag 用户状态
 */
public class User {
    private int id;
    private String userId;
    private String userPassword;
    private String userRoleId;
    private int flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRoleId='" + userRoleId + '\'' +
                ", flag=" + flag +
                '}';
    }
}
