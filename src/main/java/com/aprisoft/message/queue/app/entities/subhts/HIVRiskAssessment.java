package com.aprisoft.message.queue.app.entities.subhts;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HIVRiskAssessment {

    public Boolean everHadSexualIntercourse;
    public Boolean bloodTransfusionInLast3Months;
    public Boolean unprotectedSexWithCasualPartnerInLast3Months;
    public Boolean unprotectedSexWithRegularPartnerInLast3Months;
    public Boolean moreThan1SexPartnerDuringLast3Months;
    public Boolean stiInLast3Months;
}
