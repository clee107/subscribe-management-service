package com.subscribe.management.config;

import com.subscribe.management.config.enums.TransactionType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? TransactionType.READ_ONLY : TransactionType.WRITE;
    }

}
