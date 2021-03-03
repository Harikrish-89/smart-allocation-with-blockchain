package com.cognizant.osp.smartallocation.oabcs.request.builders;

import com.cognizant.osp.smartallocation.oabcs.request.entities.QueryTransactionRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ReviewedAllocationRequestBuilder extends QueryTransactionRequestBuilder {

    final static Logger log = LoggerFactory.getLogger(ReviewedAllocationRequestBuilder.class);

    public HttpEntity<QueryTransactionRequestEntity> buildForApproveAllocation(String requestId, String status,
                                                                               String creds, String role) {

        return new HttpEntity<QueryTransactionRequestEntity>(
                getQueryTransactionEntity("reviewAllocationChange", Arrays.asList(role,requestId,status)),getHeaders(creds));
    }
}
