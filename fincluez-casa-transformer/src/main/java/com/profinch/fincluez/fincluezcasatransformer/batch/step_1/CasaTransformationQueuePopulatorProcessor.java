package com.profinch.fincluez.fincluezcasatransformer.batch.step_1;

import com.profinch.fincluez.fincluezcasatransformer.constants.ModuleCode;
import com.profinch.fincluez.fincluezcasatransformer.models.TransformationQueueModel;
import com.profinch.fincluez.finclueztlibrary.constants.ProcessStatus;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.TransformationQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
@StepScope
public class CasaTransformationQueuePopulatorProcessor implements ItemProcessor<TransformationQueueModel, TransformationQueue> {

    private Logger log = LoggerFactory.getLogger(CasaTransformationQueuePopulatorProcessor.class);


    @Override
    public TransformationQueue process(TransformationQueueModel transformationQueueModel) throws Exception {
        log.debug("Inside Processor with {}", transformationQueueModel.toString());

        TransformationQueue transformationQueue = new TransformationQueue();
        transformationQueue.setBranchCode(transformationQueueModel.getBranch_code());
        transformationQueue.setEntityCode(transformationQueueModel.getEntity_code());
        transformationQueue.setElRunDate(transformationQueueModel.getEl_run_date());
        transformationQueue.setReferenceNumber(transformationQueueModel.getCustomer_account_number());
        transformationQueue.setTransformationProcessStatus(ProcessStatus.UNPROCESSED);
        transformationQueue.setQueuedTimeStamp(new Timestamp(new Date().getTime()));
        transformationQueue.setModule(ModuleCode.CASA.toString());
        log.debug("Processor returning CTQ {}", transformationQueue.toString());
        return transformationQueue;
    }
}
