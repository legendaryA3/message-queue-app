package com.aprisoft.message.queue.app.entities.subhts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PretestCounseling {

    public KnowledgeAssessment knowledgeAssessment;
    protected HIVRiskAssessment hivRiskAssessment;
    protected ClinicalTBScreening clinicalTBScreening;
    protected SyndromicSTIScreening syndromicSTIScreening;
}
