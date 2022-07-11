package com.aprisoft.message.queue.app.entities.entity.views;

import com.aprisoft.message.queue.app.entities.Adhere;
import com.aprisoft.message.queue.app.entities.Clinic;
import com.aprisoft.message.queue.app.entities.ClinicAdverseDrugReaction;
import com.aprisoft.message.queue.app.entities.OpportunisticInfection;
import com.aprisoft.message.queue.app.entities.*;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.Mapping;
import com.blazebit.persistence.view.MappingSingular;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@EntityView(Clinic.class)
public interface ClinicView {

    @Mapping("patient.uuid")
    String getPatientUuid(); // patient;

    @Mapping("patient.facility.id")
    Long getFacilityId();

    @Mapping("dateVisit")
    LocalDate getDateVisit();

    @Mapping("clinicStage")
    String getClinicStage();

    @Mapping("funcStatus")
    String getFuncStatus();

    @Mapping("tbStatus")
    String getTbStatus();

    @Mapping("viralLoad")
    Double getViralLoad();

    @Mapping("cd4")
    Double getCd4();

    @Mapping("cd4p")
    Double getCd4p();

    @Mapping("regimenType.id")
    Long getRegimenType();

    @Mapping("regimen.id")
    Long getRegimen();

    @Mapping("bodyWeight")
    Double getBodyWeight();

    @Mapping("height")
    Double getHeight();

    @Mapping("waist")
    Double getWaist();

    @Mapping()
    String getBp();

    @Mapping("pregnant")
    Boolean getPregnant();

    @Mapping("lmp")
    LocalDate getLmp();

    @Mapping("breastfeeding")
    Boolean getBreastfeeding();

    @Mapping("oiScreened")
    String getOiScreened();

    @Mapping("stiIds")
    String getStiIds();

    @Mapping("stiTreated")
    String getStiTreated();

    @MappingSingular
    Set<OpportunisticInfection> getOpportunisticInfections();

    @Mapping("adrScreened")
    String getAdrScreened();

    @MappingSingular
    Set<ClinicAdverseDrugReaction> getAdverseDrugReactions();

    @Mapping("adherenceLevel")
    String getAdherenceLevel();

    @MappingSingular
    Set<Adhere> getAdheres();

    @Mapping("commence")
    Boolean getCommence();

    @Mapping("nextAppointment")
    LocalDate getNextAppointment();

    @Mapping("notes")
    String getNotes();

    @Mapping("gestationalAge")
    String getGestationalAge();

    @Mapping("maternalStatusArt")
    String getMaternalStatusArt();

    @Mapping("extra")
    JsonNode getExtra();

    @Mapping("uuid")
    String getUuid();

    LocalDateTime getLastModified();

    String getSyncStatus();

}
