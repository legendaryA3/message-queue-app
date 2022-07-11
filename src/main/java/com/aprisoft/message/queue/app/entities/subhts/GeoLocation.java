package com.aprisoft.message.queue.app.entities.subhts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class GeoLocation implements Serializable {

    public GeoLocation() {};

    @JsonProperty("latitude")
    public Double latitude;

    @JsonProperty("longitude")
    public Double longitude;
}
