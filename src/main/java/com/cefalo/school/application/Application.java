package com.cefalo.school.application;

/**
 * Created by atiqul on 7/19/2018.
 */
public interface Application {
  void setUserName(String userName);
  void setAuthToken(String authToken);
  void setApplicationType(ApplicationType applicationType);
  public ApplicationType getApplicationType();
}
