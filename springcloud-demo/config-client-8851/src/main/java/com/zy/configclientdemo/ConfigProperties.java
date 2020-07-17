package com.zy.configclientdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "data")
public class ConfigProperties {
    private String env;
    private User user;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ConfigProperties{" +
                "env='" + env + '\'' +
                ", user=" + user +
                '}';
    }

   
}
