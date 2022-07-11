package com.aprisoft.message.queue.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClinicAdverseDrugReaction implements Serializable {

    private String severity;

    @JsonProperty("adverseDrugReaction")
    @JsonIgnoreProperties(ignoreUnknown = true,  value = "new")
    private AdverseDrugReaction adverseDrugReaction;

}
