package com.aprisoft.message.queue.app.entities.entity.views;

import com.aprisoft.message.queue.app.entities.DMScreen;
import com.blazebit.persistence.view.EntityView;

@EntityView(DMScreen.class)
public interface DMScreenView {

    Long getId();

    String getDescription();
}
