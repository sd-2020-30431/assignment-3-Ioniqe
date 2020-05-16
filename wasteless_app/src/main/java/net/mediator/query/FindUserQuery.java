package net.mediator.query;

import net.mediator.interfaces.Request;

public class FindUserQuery implements Request {
    private String username;
    private String password;

    public FindUserQuery(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public FindUserQuery(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
