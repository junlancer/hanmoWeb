package bean;

import java.io.Serializable;

public class User implements Serializable {
    /**
     * 用户id
     */
    public int userId;
    /**
     * 用户角色，默认0(未激活)
     * 一级管理员-1，二级管理员-2，等等类推
     * 普通用户按等级递增(1,2,3...)
     */
    public int role;
    /**
     * 用户名
     */
    public String userName;
    /**
     * 用户手机号码，可空(nichen空的话为用户名)
     */
    public String userTel;
    /**
     * 用户搜索内容mima
     */
    public String userSec;

    public String userIp;

    public String userTime;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", role=" + role +
                ", userName='" + userName + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userSec='" + userSec + '\'' +
                ", userIp='" + userIp + '\'' +
                ", userTime='" + userTime + '\'' +
                '}';
    }
}
