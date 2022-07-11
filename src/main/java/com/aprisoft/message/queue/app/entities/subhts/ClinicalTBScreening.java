package com.aprisoft.message.queue.app.entities.subhts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalTBScreening {

    public Boolean currentlyCough;
    public Boolean weightLoss;
    public Boolean fever;
    public Boolean nightSweats;
    public Boolean lymphadenopath;
}
