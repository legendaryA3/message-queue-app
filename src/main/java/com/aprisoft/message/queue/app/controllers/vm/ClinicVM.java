package com.aprisoft.message.queue.app.controllers.vm;

import com.aprisoft.spring_messaging.entities.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClinicVM {

    private Long id;
    private Long facilityId;
    /*private UUID patientUUID;
    private LocalDate dateVisit;
    private String clinicStage;
    private String funcStatus;
    private String tbStatus;
    private Double viralLoad;
    private Double cd4;
    private Double cd4p;
    private Long regimenTypeId;
    private Long regimenId;
    private Double bodyWeight;
    private Double height;
    private Double waist;
    private String bp;
    private Boolean pregnant;
    private LocalDate lmp;
    private Boolean breastfeeding;
    private String oiScreened;
    private String stiIds;
    private String stiTreated;
    Set<OpportunisticInfection> opportunisticInfections = new HashSet<>();
    private String adrScreened;
    Set<ClinicAdverseDrugReaction> adverseDrugReactions = new HashSet<>();
    private String adherenceLevel;
    Set<Adhere> adheres = new HashSet<>();
    private Boolean commence = false;
    private LocalDate nextAppointment;
    private String notes;
    private String gestationalAge;
    private String maternalStatusArt;
    private JsonNode extra;
    private LocalDate lastModified;
*/
}
