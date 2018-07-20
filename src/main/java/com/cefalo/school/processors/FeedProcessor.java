package com.cefalo.school.processors;

import com.cefalo.school.mapper.FeedMapper;
import com.cefalo.school.operators.FeedOperator;

public abstract class FeedProcessor {
    protected FeedOperator operationProcessor;
    protected FeedMapper feedMapper;
}
