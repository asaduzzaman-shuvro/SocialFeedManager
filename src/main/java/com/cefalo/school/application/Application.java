package com.cefalo.school.application;

import com.cefalo.school.factories.FeedProcessorFactory;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.processors.FeedProcessor;

import java.rmi.server.UID;
import java.util.List;

/**
 * Created by atiqul on 7/19/2018.
 */
public interface Application {
  void setUserName(String userName);
  void setAuthToken(String authToken);
  void setApplicationType(ApplicationType applicationType);
  void setApplicationIdentifier(UID identifier);
  UID getApplicationIdentifier();
  ApplicationType getApplicationType();
}
