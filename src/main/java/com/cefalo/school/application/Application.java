package com.cefalo.school.application;

import java.util.UUID;

/**
 * Created by atiqul on 7/19/2018.
 */
public interface Application {
  void setUserName(String userName);
  String getUserName();
  String getUserId();
  void setAuthToken(String authToken);
  public String getAuthToken();
  UUID getApplicationIdentifier();
  ApplicationType getApplicationType();
  String getUserDisplayName();
  void setUserDisplayName(String displayName);
}
