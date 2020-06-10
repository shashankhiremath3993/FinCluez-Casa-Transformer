package com.profinch.fincluez.fincluezcasatransformer.batch.step_1;

import com.profinch.fincluez.finclueztlibrary.entities.martEntities.TransformationQueue;
import com.profinch.fincluez.finclueztlibrary.repo.martRepo.TransformationQueueRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(rollbackFor = Exception.class)
public class CasaTransformationQueuePopulatorWriter implements ItemWriter<TransformationQueue> {

    TransformationQueueRepo transformationQueueRepo;
    private Logger log = LoggerFactory.getLogger(CasaTransformationQueuePopulatorWriter.class);

    @Autowired
    public CasaTransformationQueuePopulatorWriter(TransformationQueueRepo transformationQueueRepo) {
        this.transformationQueueRepo = transformationQueueRepo;
    }

    @Override
    public void write(List<? extends TransformationQueue> casaTransformationQueueList) throws Exception {
        log.debug("Inside CTQ Writer with {}", casaTransformationQueueList.size());
        transformationQueueRepo.saveAll(casaTransformationQueueList);
        log.debug("written CTQ");
    }
}
