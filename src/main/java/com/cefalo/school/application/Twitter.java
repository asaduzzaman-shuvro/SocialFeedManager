package com.cefalo.school.application;

import java.rmi.server.UID;

public class Twitter implements Application {
    private String userName;
    private String authToken;
    private ApplicationType applicationType;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    public void setApplicationIdentifier(UID identifier) {

    }

    public UID getApplicationIdentifier() {
        return null;
    }

    public String getUserName() {
        return userName;
    }

    public String getAuthToken() {
        return authToken;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }
}
