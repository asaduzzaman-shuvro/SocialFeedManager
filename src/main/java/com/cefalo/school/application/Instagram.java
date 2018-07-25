package com.cefalo.school.application;

import java.util.UUID;

public class Instagram implements Application{

    private String userName;
    private String authToken;
    private ApplicationType applicationType;
    private UUID applicationIdentifier;

    public Instagram(){
        this.applicationIdentifier = UUID.randomUUID();
        this.applicationType = ApplicationType.INSTAGRAM;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public UUID getApplicationIdentifier() {
        return applicationIdentifier;
    }

    @Override
    public ApplicationType getApplicationType() {
        return applicationType;
    }
}
