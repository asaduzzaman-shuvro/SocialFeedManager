package com.cefalo.school.services;

import com.cefalo.school.Model.Content;
import com.cefalo.school.Model.FeedItem;

import java.util.List;

public interface FeedOperationProcessor {
    boolean postUpdate(FeedItem item);
}
