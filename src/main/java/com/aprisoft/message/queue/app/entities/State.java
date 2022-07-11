package com.aprisoft.message.queue.app.entities;

import com.blazebit.persistence.view.EntityView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A State.
 */
@Entity
@Table(name = "state")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "name", "code"})
public final class State implements Serializable, Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    /*@NotNull
    @Column(nullable = false)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Country country;
    */

    @Override
    public boolean isNew() {
        return id == null;
    }

    @EntityView(State.class)
    public interface StateView {
        Long getId();
    }
}
