package com.cefalo.school.application;

import java.util.UUID;

public class Instagram implements Application{

    private String userName = "";
    private String authToken = "";
    private ApplicationType applicationType;
    private UUID applicationIdentifier;
    private String  userId = "";
    private String displayName = "";

    public Instagram(){
        this.applicationIdentifier = UUID.randomUUID();
        this.applicationType = ApplicationType.INSTAGRAM;
        this.userId = "1547627005";
        System.out.println("Instagram: " + applicationIdentifier);
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    @Override
    public void setDisplayName(String name) {
        this.displayName = name;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    public String getAuthToken() {
        return authToken;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    @Override
    public String getUserDisplayName() {
        return displayName;
    }

    @Override
    public void setUserDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
