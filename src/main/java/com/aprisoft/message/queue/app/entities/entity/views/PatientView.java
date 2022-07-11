package com.aprisoft.message.queue.app.entities.entity.views;

import com.aprisoft.message.queue.app.entities.Facility;
import com.aprisoft.message.queue.app.entities.Patient;
import com.aprisoft.message.queue.app.entities.enumerations.ClientStatus;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.Mapping;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;
import java.time.LocalDateTime;


@EntityView(Patient.class)
public interface PatientView {
    @Mapping("uuid")
    String getUuid();

    @Mapping("lastModified")
    LocalDateTime getLastModified();

    @Mapping("facility.id")
    Long getFacilityId();

    Facility.FacilityView getFacility();

    @Mapping("hospitalNum")
    String getHospitalNum();

    @Mapping("uniqueId")
    String getUniqueId();

    @Mapping("surname")
    String getSurname();

    @Mapping("otherNames")
    String getOtherNames();

    @Mapping("gender")
    String getGender();

    @Mapping("dateBirth")
    LocalDate getDateBirth();

    @Mapping("maritalStatus")
    String getMaritalStatus();

    @Mapping("education")
    String getEducation();

    @Mapping("occupation")
    String getOccupation();

    @Mapping("address")
    String getAddress();

    @Mapping("phone")
    String getPhone();

    @Mapping("dobEstimated")
    Boolean getDobEstimated();

    @Mapping("nextOfKin")
    String getNextOfKin();

    @Mapping("nextOfKinAddress")
    String getNextOfKinAddress();

    @Mapping("nextOfKinPhone")
    String getNextOfKinPhone();

    @Mapping("nextOfKinRelationship")
    String getNextOfKinRelationship();

    @Mapping("entryPoint")
    String getEntryPoint();

    @Mapping("targetGroup")
    String getTargetGroup();

    @Mapping("dateConfirmedHiv")
    LocalDate getDateConfirmedHiv();

    @Mapping("dateEnrolledPMTCT")
    LocalDate getDateEnrolledPMTCT();

    @Mapping("sourceReferral")
    String getSourceReferral();

    @Mapping("timeHivDiagnosis")
    String getTimeHivDiagnosis();

    @Mapping("tbStatus")
    String getTbStatus();

    @Mapping("pregnant")
    Boolean getPregnant();

    @Mapping("breastfeeding")
    Boolean getBreastfeeding();

    @Mapping("dateRegistration")
    LocalDate getDateRegistration();

    @Mapping("statusAtRegistration")
    ClientStatus getStatusAtRegistration();

    @Mapping("enrollmentSetting")
    String getEnrollmentSetting();

    @Mapping("caseManager.id")
    Long getCaseManager();

    @Mapping("dateStarted")
    LocalDate getDateStarted();

    @Mapping("sendMessage")
    Boolean getSendMessage();

    @Mapping("partnerInformation.id")
    Long getPartnerInformation();

    @Mapping("lga.id")
    Long getLgaId();

    @Mapping("extra")
    JsonNode getExtra();

    @Mapping("htsId")
    Long getHtsId();

    @Mapping("archived")
    Boolean getArchived();

    String getSyncStatus();

}
