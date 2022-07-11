package com.aprisoft.message.queue.app.entities.subhts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.text.html.Option;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostTestCounseling {

    public String hivResult;
    public String testedForHIVBeforeWithinThisYear;
    public Boolean hivRequestAndResultFormSignedByTester;
    public Boolean hivRequestAndResultFormFilledWithCTIForm;
    public Boolean clientReceivedHIVTestResult;
    public Boolean postTestCounsellingDone;
    public Boolean riskReductionPlanDeveloped;
    public Boolean postTestDisclosurePlanDeveloped;
    public Boolean willBringPartnerForHIVTesting;
    public Boolean willBringOwnChildrenForHIVTesting;
    public Boolean providedWithInformationOnFPAndDualContraception;
    public Boolean clientOrPartnerUseFPMethodsOtherThanCondoms;
    public Boolean clientOrPartnerUseCondomsAsOneFPMethods;
    public Boolean correctCondomUseDemonstrated;
    public Boolean condomsProvidedToClient;
    public Boolean clientReferredToOtherServices;
}
