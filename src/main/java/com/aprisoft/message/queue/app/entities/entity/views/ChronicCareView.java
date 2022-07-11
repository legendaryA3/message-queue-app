package com.aprisoft.message.queue.app.entities.entity.views;

import com.aprisoft.message.queue.app.entities.ChronicCareDM;
import com.aprisoft.message.queue.app.entities.ChronicCareTB;
import com.aprisoft.spring_messaging.entities.*;
import com.blazebit.persistence.view.Mapping;

import java.time.LocalDate;
import java.util.Set;

public interface ChronicCareView {

    @Mapping("patient.uuid")
    String getPatientUuid();

    LocalDate getDateVisit();

    String getClientType();

    String getCurrentStatus();

    String getClinicStage();

    String getPregnancyStatus();

    Double getLastCd4();

    LocalDate getDateLastCd4();

    LocalDate getDateCompletedTBTreatment();

    String getTbScreeningStatus();

    LocalDate getTbDateSampleCollected();

    Double getLastViralLoad();

    LocalDate getDateLastViralLoad();

    Boolean getEligibleCd4();

    Boolean getEligibleViralLoad();

    Boolean getPlhivSymtomaticHiv();

    Boolean getPlhivAsymtomaticCD4L500();

    Boolean getPlhivActiveTb();

    Boolean getPlhivPregnantAfter1stTrimester();

    Boolean getPlhivL5Years();

    Boolean getIpt();

    Boolean getInh();

    Boolean getTbTreatment();

    LocalDate getDateStartedTbTreatment();

    Boolean getTbReferred();

    Boolean getEligibleIpt();

    Double getBodyWeight();

    Double getHeight();

    String getBmi();

    Double getMuac();

    String getMuacPediatrics();

    String getMuacPregnant();

    Boolean getSupplementaryFood();

    Boolean getNutritionalStatusReferred();

    Boolean getSexuallyAbused();

    Boolean getSexuallyAbusedReferred();

    Boolean getEssentialsDeniedByPartner();

    Boolean getEssentialsDeniedByPartnerReferred();

    Boolean getHypertensive();

    Boolean getFirstHypertensive();

    Boolean getBpAbove14090();

    Boolean getBpReferred();

    Boolean getDiabetic();

    Boolean getFirstDiabetic();

    Boolean getDmReferred();

    String getMissedArvs();

    Boolean getMissedArvsServicesProvided();

    Boolean getStatusDisclosedToPartner();

    Boolean getPartnerStatusKnown();

    Boolean getUseCondomsAlways();

    Boolean getUseCondomsAlwaysServicesProvided();

    Boolean getOpportunisticInfections();

    Integer getMissedCotrim();

    Integer getWeeklyAlcoholConsumption();

    Boolean getWeeklyAlcoholConsumptionServicesProvided();

    Boolean getWashServicesProvided();

    Boolean getUseInsecticideNets();

    Boolean getCervicalCancerScreening();

    Boolean getActiveMemberOfSG();

    Boolean getFamilyPlanning();

    Boolean getBasicCareKits();

    Boolean getDisclosureCounseling();

    Boolean getSocialServices();

    Boolean getLegalServices();

    Boolean getLinkageToIGA();

    Boolean getOtherServices();

    Boolean getCervicalCancerScreeningWithinPastYear();

    Boolean getCervicalCancerScreeningWithinPastYearReferred();

    Boolean getWantPregnancyWithinAYear();

    Boolean getWantPregnancyWithinAYearReferred();

    Boolean getCurrentlyUsingContraceptive();

    Boolean getCurrentlyUsingContraceptiveReferred();

    Boolean getUseInsecticideBedNet();

    Boolean getUseInsecticideBedNetReferred();

    Boolean getPregnantIntermittentPreventiveTherapy();

    Boolean getPregnantIntermittentPreventiveTherapyReferred();

    @Mapping("dddoutlet.uuid")
    Long getDDDOutletId();

    Set<ChronicCareDM> getDmScreens();

    Set<ChronicCareTB> getTbScreens();

}
