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

  public void setApplicationType(ApplicationType applicationType) {
    this.applicationType = applicationType;
  }

  public void setApplicationIdentifier(UUID identifier) {
    this.applicationIdentifier = new UUID(23,45);

  }

  public UUID getApplicationIdentifier() {
    return applicationIdentifier;
  }
}
