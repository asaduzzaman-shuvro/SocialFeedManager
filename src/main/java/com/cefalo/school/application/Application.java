package com.cefalo.school.application;

import java.util.UUID;

/**
 * Created by atiqul on 7/19/2018.
 */
public interface Application {
  void setUserName(String userName);
  void setAuthToken(String authToken);
  void setApplicationType(ApplicationType applicationType);
  void setApplicationIdentifier(UUID identifier);
  UUID getApplicationIdentifier();
  ApplicationType getApplicationType();
}
