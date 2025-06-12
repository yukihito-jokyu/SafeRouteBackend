package com.osc.saferoute.domain.model;

public class User {
    private UserId user_id;
    private UserName nickname;

    public User(UserId user_id, UserName nickname) {
        this.user_id = user_id;
        this.nickname = nickname;
    }

    public void changeName(UserName newName) {
        if (newName == null || newName.value().isBlank()) {
            throw new IllegalArgumentException("名前は空にできません。");
        }
        this.nickname = newName;
    }

    public UserId id() {
        return user_id;
    }

    public UserName name() {
        return nickname;
    }
}