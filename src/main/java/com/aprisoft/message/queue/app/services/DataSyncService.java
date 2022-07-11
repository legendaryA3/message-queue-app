package com.aprisoft.message.queue.app.services;

import com.aprisoft.message.queue.app.entities.DataSync;
import com.aprisoft.message.queue.app.repositories.DataSyncRepository;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Service
public class DataSyncService {

    private final DataSyncRepository dataSyncRepository;

    public final EntityManager em;
    public final CriteriaBuilderFactory cbf;
    private final EntityViewManager evm;

    public DataSyncService(
            DataSyncRepository dataSyncRepository,
                           EntityManager em,
                           CriteriaBuilderFactory cbf,
                           EntityViewManager evm) {
        this.dataSyncRepository = dataSyncRepository;
        this.em = em;
        this.cbf = cbf;
        this.evm = evm;
    }

    public DataSync getDataSync (UUID patientUUID) {

        return dataSyncRepository.getDataSyncByPatientId(patientUUID);
    }

    public List<DataSync> getAllDataSync(){
        return dataSyncRepository.findAll();
    }

    // This service get max timestamp for all tables
    public DataSync.MaxSyncTime getTablesSyncMaxDateTime(Long facilityId) {
        var settings = EntityViewSetting.create(DataSync.MaxSyncTime.class);
        var cb = cbf.create(em, DataSync.MaxSyncTime.class)
                .from(DataSync.class)
                .where("facilityId").eq(facilityId);
        return evm.applySetting(settings, cb).getSingleResult();
        // return dataSyncRepository.maxSynDate(facilityId);
    }

    public void save (DataSync dataSync) {
        dataSyncRepository.save(dataSync);
    }
}
