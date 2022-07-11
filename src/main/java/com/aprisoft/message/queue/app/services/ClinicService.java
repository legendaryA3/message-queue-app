package com.aprisoft.message.queue.app.services;

import com.aprisoft.message.queue.app.entities.Clinic;
import com.aprisoft.message.queue.app.entities.Laboratory;
import com.aprisoft.message.queue.app.entities.entity.views.ClinicView;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClinicService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class.getSimpleName());

    public final EntityManager em;
    public final CriteriaBuilderFactory cbf;
    private final EntityViewManager evm;

    public ClinicService(EntityManager em, CriteriaBuilderFactory cbf, EntityViewManager evm) {
        this.em = em;
        this.cbf = cbf;
        this.evm = evm;
    }

    public List<ClinicView> getClinicDataByMaxTime(LocalDateTime patientMaxTime) {
        var settings = EntityViewSetting.create(ClinicView.class);
        var cb = cbf.create(em, ClinicView.class)
                .from(Clinic.class)
                .orderByAsc("id");
        if (patientMaxTime != null){
            cb = cbf.create(em, ClinicView.class)
                    .from(Clinic.class)
                    .where("lastModified").gt(patientMaxTime)
                    .orderByAsc("id");
        }
        return evm.applySetting(settings, cb).getResultList();

    }

    @Transactional
    public void updateSyncStatus(String uuid, String status) {
        var cb = cbf.update(em, Laboratory.class)
                .set("syncStatus", status)
                .where("uuid").eq(uuid);
        cb.executeUpdate();
    }
}
