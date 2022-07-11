package com.aprisoft.message.queue.app.entities.entity.views;

import com.aprisoft.message.queue.app.entities.*;
import com.aprisoft.message.queue.app.entities.enumerations.ClientStatus;
import com.aprisoft.spring_messaging.entities.*;
import com.blazebit.persistence.view.*;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@EntityView(Patient.class)
public interface InitialPatientView extends Facility.FacilityView {

    @IdMapping
    Long getId();

    String getUuid();

    LocalDateTime getLastModified();

    Facility.FacilityView getFacility();

    String getHospitalNum();

    String getUniqueId();

    String getSurname();

    String getOtherNames();

    String getGender();

    LocalDate getDateBirth();

    String getMaritalStatus();

    String getEducation();

    String getOccupation();

    String getAddress();

    String getPhone();

    Boolean getDobEstimated();

    String getNextOfKin();

    String getNextOfKinAddress();

    String getNextOfKinPhone();

    String getNextOfKinRelationship();

    String getEntryPoint();

    String getTargetGroup();

    LocalDate getDateConfirmedHiv();

    LocalDate getDateEnrolledPMTCT();

    String getSourceReferral();

    String getTimeHivDiagnosis();

    String getTbStatus();

    Boolean getPregnant();

    Boolean getBreastfeeding();

    LocalDate getDateRegistration();

    ClientStatus getStatusAtRegistration();

    String getEnrollmentSetting();

    CaseManager.CaseManagerView getCaseManager();

    LocalDate getDateStarted();

    Boolean getSendMessage();

    @Mapping("partnerInformation.id")
    Long getPartnerInformation();

    Province.ProvinceView getLga();

    JsonNode getExtra();

    Long getHtsId();

    Boolean getArchived();

    // HtsNew getHtsNew();

    // Set<Pharmacy.InitialPharmacyView> getPharmacies();

    // Set<Clinic.InitialClinicView> getClinics();

    // Set<Laboratory.InitialLaboratoryView> getLaboratories();

    // Set<ChronicCareView> getChronicCares();

    Set<ChronicCare.InitialChronicCareView> getChronicCares();


}
