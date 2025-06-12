package com.osc.saferoute.domain.model;

public class User {
    private UserId id;
    private UserName name;

    public User(UserId id, UserName name) {
        this.id = id;
        this.name = name;
    }

    public void changeName(UserName newName) {
        if (newName == null || newName.value().isBlank()) {
            throw new IllegalArgumentException("名前は空にできません。");
        }
        this.name = newName;
    }

    public UserId id() {
        return id;
    }

    public UserName name() {
        return name;
    }
}