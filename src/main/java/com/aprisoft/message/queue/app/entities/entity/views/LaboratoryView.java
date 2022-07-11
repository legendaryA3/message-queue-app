package com.aprisoft.message.queue.app.entities.entity.views;

import com.aprisoft.message.queue.app.entities.Laboratory;
import com.aprisoft.message.queue.app.entities.LaboratoryLine;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.Mapping;
import com.blazebit.persistence.view.MappingSingular;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@EntityView(Laboratory.class)
public interface LaboratoryView {

    @Mapping("uuid")
    String getUuid();
    @Mapping("patient.uuid")
    String getPatientUuid();

    @Mapping("patient.facility.id")
    Long getFacilityId();

    LocalDate getDateResultReceived();

    LocalDate getDateSampleCollected();

    LocalDate getDateAssay();

    String getLabNo();

    JsonNode getExtra();

    @MappingSingular
    Set<LaboratoryLine> getLines();

    @Mapping("archived")
    Boolean getArchived();

    @Mapping("lastModified")
    LocalDateTime getLastModified();

    String getSyncStatus();

}
