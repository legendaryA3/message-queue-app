package com.aprisoft.message.queue.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "opportunistic_infection")
@Data
@EqualsAndHashCode(of = "description")
@JsonIgnoreProperties("new")
public class OpportunisticInfection implements Serializable, Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @JsonIgnore
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPTION")
    @JsonIgnore
    private String description;

    @Size(max = 15)
    @Column(name = "CLINIC_STAGE")
    @JsonIgnore
    private String clinicStage;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
