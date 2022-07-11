package com.aprisoft.message.queue.app.entities.entity.views;

import com.blazebit.persistence.view.Mapping;

public interface ChronicCareTBView {

    @Mapping("dmScreen.uuid")
    Long getDmScreenUuid();

    String getDescription();
}
