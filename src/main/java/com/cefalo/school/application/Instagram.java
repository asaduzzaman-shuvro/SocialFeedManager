package com.cefalo.school.application;

import java.util.UUID;

public class Instagram implements Application{

    private String userName;
    private String authToken;
    private ApplicationType applicationType;
    private UUID applicationIdentifier;
    private String  userId;

    public Instagram(){
        this.applicationIdentifier = UUID.randomUUID();
        this.applicationType = ApplicationType.INSTAGRAM;
        this.userId = "1547627005";
        System.out.println("Instagram: " + applicationIdentifier);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public UUID getApplicationIdentifier() {
        return applicationIdentifier;
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
