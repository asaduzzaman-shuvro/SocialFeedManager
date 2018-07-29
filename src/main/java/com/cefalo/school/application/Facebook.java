package com.cefalo.school.application;

import java.util.UUID;

/**
 * Created by atiqul on 7/19/2018.
 */
public class Facebook implements Application {

  private String userName = "";
  private String authToken = "";
  private ApplicationType applicationType;
  private UUID applicationIdentifier;
  private String  userId = "";
  private String displayName = "";

  public Facebook(){
    this.applicationIdentifier = UUID.randomUUID();
    this.applicationType = ApplicationType.FACEBOOK;
    System.out.println("Facebook: " + applicationIdentifier);
  }

  @Override

  public String getAuthToken() {
    return authToken;
  }

  public ApplicationType getApplicationType() {
    return applicationType;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Override
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

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
