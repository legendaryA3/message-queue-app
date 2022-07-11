package com.aprisoft.message.queue.app.entities;

import com.aprisoft.message.queue.app.entities.subhts.*;
import com.aprisoft.spring_messaging.entities.subhts.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity()
@Table(name = "hts_new")
@Data
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class HtsNew implements Serializable, Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "client_code")
    public String clientCode;

    @Column(name = "target_group")
    public String targetGroup;

    @JoinColumn(name = "facility_id")
    @ManyToOne
    public Facility facility;

    @Column(name = "referred_from")
    public String referredFrom;

    @Column(name = "setting")
    public String setting;

    @Column(name = "surname")
    public String surname;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "middle_name")
    public String middleName;

    @Column(name = "client_address")
    public String clientAddress;

    @Column(name = "age")
    public Long age;

    @Column(name = "date_visit")
    public LocalDate dateVisit;

    @Column(name = "date_of_birth")
    public LocalDate dateOfBirth;

    @Column(name = "gender")
    public String gender;

    @Column(name = "phone_number")
    public String phoneNumber;

    @Column(name = "first_time_visit")
    public String firstTimeVisit;

    @JoinColumn(name = "state_of_residence")
    @ManyToOne
    private State state;

    @ManyToOne
    @JoinColumn(name = "lga_of_residence")
    private Province lga;

    @Column(name = "marital_status")
    public String maritalStatus;

    @Column(name = "number_of_wives")
    public Long numberOfWives;

    @Column(name = "number_of_children")
    public Long numberOfChildren;

    @Column(name = "type_of_counseling")
    public String typeOfCounseling;

    @Column(name = "index_testing")
    public String indexTesting;

    @Column(name = "index_client_code")
    public String indexClientCode;

    @Column(name = "index_type")
    public String indexType;

    @Column(name = "previously_tested")
    public Boolean previouslyTested;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "pre_test_counseling")
    public PretestCounseling preTestCounseling;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "post_test_counseling")
    public PostTestCounseling postTestCounseling;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "recency_testing")
    public RecencyTesting recencyTesting;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "index_client")
    public List<IndexClient> indexClient;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "hiv_test_result")
    public HIVTestResult hivTestResult;

    @Column(name = "syphilis_testing")
    public String syphilisTesting;

    @Column(name = "hepatitis_b_testing")
    public String hepatitisBTesting;

    @Column(name = "hepatitis_c_testing")
    public String hepatitisCTesting;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    public JsonNode geoLocation;

    @Column(name = "completed_by")
    public String completedBy;

    @Column(name = "designation")
    public String designation;

    @Column(name = "user_id")
    public Long userId;

    @Column(
        name = "created_at"
    )
    @JsonIgnore
    protected LocalDateTime createdAt;
    @Column(
        name = "updated_at"
    )
    @JsonIgnore
    protected LocalDateTime updatedAt;
    @Size(
        max = 36
    )
    @Column(
        name = "hts_uuid"
    )
    protected String htsUuid;
    @Column(
        name = "prep_offered"
    )
    protected String prepOffered;
    @Column(
        name = "prep_accepted"
    )
    protected String prepAccepted;

    @Column(name = "adhoc_code")
    protected String adhocCode;

    @Column(name = "accepted_ins")
    protected String acceptedIns;

    @Column(name = "offered_ins")
    protected String offeredIns;

    @Column(name = "number_of_condom")
    protected Integer numberOfCondom;

    @Column(name = "cd4_count")
    protected Float cd4Count;

    @Column(
        name = "auto"
    )
    protected Boolean auto;

    @JoinColumn(name = "state_test_conducted")
    @ManyToOne
    private State stateTestConducted;

    @ManyToOne
    @JoinColumn(name = "lga_test_conducted")
    private Province lgaTestConducted;

    @ManyToOne
    @JoinColumn(name = "community_test_conducted")
    private Community communityTestConducted;

    /*@OneToOne
    @JoinColumn(name = "hts_new_id", referencedColumnName = "id")
    private HtsNew htsNew;*/

    @Column(name = "self_test_id")
    private UUID selfTestId;

    @Override
    public boolean isNew() {
        return id == null;
    }

    @PrePersist
    public void preSave() {
        if (htsUuid == null) htsUuid = UUID.randomUUID().toString();
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (recencyTesting == null) recencyTesting = new RecencyTesting();
        if (indexClient == null) indexClient = Collections.singletonList(new IndexClient());
    }

    @PreUpdate
    public void preUpdate() {
        if (htsUuid == null) htsUuid = UUID.randomUUID().toString();
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (recencyTesting == null) recencyTesting = new RecencyTesting();
        if (indexClient == null) indexClient = Collections.singletonList(new IndexClient());
    }
}
