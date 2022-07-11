package com.aprisoft.message.queue.app.entities.subhts;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SyndromicSTIScreening {

    public Boolean vaginalDischargeOrBurningWhenUrinating;
    public Boolean lowerAbdominalPainsWithOrWithoutVaginalDischarge;
    public Boolean urethralDischargeOrBurningWhenUrinating;
    public Boolean scrotalSwellingAndPain;
    public Boolean genitalSoreOrSwollenInguinalLymphNodes;
}
