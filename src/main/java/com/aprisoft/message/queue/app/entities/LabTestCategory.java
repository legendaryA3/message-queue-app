package com.aprisoft.message.queue.app.entities;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "LAB_TEST_CATEGORY")
@Data
public class LabTestCategory implements Serializable, Persistable<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "CATEGORY")
    private String category;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
