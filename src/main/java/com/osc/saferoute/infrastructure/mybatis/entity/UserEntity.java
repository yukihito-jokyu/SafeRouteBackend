package com.osc.saferoute.infrastructure.mybatis.entity;

public class UserEntity {
    private String user_id;
    private String nickname;

    // getters and setters
    public String getId() { return user_id; }
    public void setId(String user_id) { this.user_id = user_id; }
    public String getName() { return nickname; }
    public void setName(String nickname) { this.nickname = nickname; }
}
