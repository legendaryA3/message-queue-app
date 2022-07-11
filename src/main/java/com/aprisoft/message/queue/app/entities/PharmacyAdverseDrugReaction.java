package com.aprisoft.message.queue.app.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PharmacyAdverseDrugReaction implements Serializable {

    @JsonProperty("adverse_drug_reaction")
    private AdverseDrugReaction adverseDrugReaction;

    private String severity;
}
