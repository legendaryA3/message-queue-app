package com.aprisoft.message.queue.app.services.producers;

import com.aprisoft.message.queue.app.controllers.parameters.SyncRecordParameters;

public interface ProducerImpl {
    void produceAndSyncData(SyncRecordParameters syncRecordParameters);
}
