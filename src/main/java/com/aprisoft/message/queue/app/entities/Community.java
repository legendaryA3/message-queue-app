package com.aprisoft.message.queue.app.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Entity()
@Table(name = "communities")
@Data
public class Community implements Serializable, Persistable<UUID> {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
        parameters = {
            @Parameter(
                name = "uuid_gen_strategy_class",
                value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
            )
        }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @JoinColumn(name = "state_id")
    @ManyToOne
    private State state;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;

    @JoinColumn(name = "lga_id")
    @ManyToOne(optional = false)
    private Province lga;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "active")
    private Boolean active;

    @Override
    public boolean isNew() {
        return false;
    }
}
