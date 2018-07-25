package com.cefalo.school.application;

import java.util.UUID;

/**
 * Created by atiqul on 7/19/2018.
 */
public class Facebook implements Application {

  private String userName;
  private String authToken;
  private ApplicationType applicationType;
  private UUID applicationIdentifier;

  public Facebook(){
    this.applicationIdentifier = UUID.randomUUID();
    this.applicationType = ApplicationType.FACEBOOK;
    System.out.println("Facebook: " + applicationIdentifier);
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

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  public UUID getApplicationIdentifier() {
    return applicationIdentifier;
  }
}
