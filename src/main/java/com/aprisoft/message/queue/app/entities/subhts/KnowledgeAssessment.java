package com.aprisoft.message.queue.app.entities.subhts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeAssessment {

    public Boolean previouslyTestedHIVNegative;
    public Boolean clientInformedAboutHIVTransmissionRoutes;
    public Boolean clientPregnant;
    public Boolean clientInformedOfHIVTransmissionRiskFactors;
    public Boolean clientInformedAboutPreventingHIV;
    public Boolean clientInformedAboutPossibleTestResults;
    public Boolean informedConsentForHIVTestingGiven;
}
