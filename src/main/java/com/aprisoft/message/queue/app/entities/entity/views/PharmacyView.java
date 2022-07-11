package com.aprisoft.message.queue.app.entities.entity.views;

import com.aprisoft.message.queue.app.entities.Pharmacy;
import com.aprisoft.message.queue.app.entities.PharmacyAdverseDrugReaction;
import com.aprisoft.message.queue.app.entities.PharmacyLine;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.Mapping;
import com.blazebit.persistence.view.MappingSingular;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@EntityView(Pharmacy.class)
public interface PharmacyView {

    @Mapping("uuid")
    String getUuid();

    @Mapping("patient.uuid")
    String getPatientUuid();

    @Mapping("patient.facility.id")
    Long getFacilityId();

    LocalDate getDateVisit();

    Boolean getAdrScreened();

    Boolean getPrescriptionError();

    Boolean getAdherence();

    String getMmdType();

    LocalDate getNextAppointment();

    JsonNode getExtra();

    @MappingSingular
    Set<PharmacyLine> getLines();

    @MappingSingular
    Set<PharmacyAdverseDrugReaction> getAdverseDrugReactions();

    @Mapping("archived")
    Boolean getArchived();

    @Mapping("lastModified")
    LocalDateTime getLastModified();

    String getSyncStatus();

}
