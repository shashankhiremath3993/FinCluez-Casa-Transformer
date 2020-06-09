package com.profinch.fincluez.fincluezcasatransformer.batch.step_1;

import com.profinch.fincluez.fincluezcasatransformer.constants.ModuleCode;
import com.profinch.fincluez.fincluezcasatransformer.models.CasaTransformerQueuePopulatorResponse;
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
public class CasaTransformerQueuePopulatorProcessor implements ItemProcessor<CasaTransformerQueuePopulatorResponse, TransformationQueue> {

    private Logger log = LoggerFactory.getLogger(CasaTransformerQueuePopulatorProcessor.class);


    @Override
    public TransformationQueue process(CasaTransformerQueuePopulatorResponse casaTransformerQueuePopulatorResponse) throws Exception {
        log.debug("Inside Processor with {}", casaTransformerQueuePopulatorResponse.toString());

        TransformationQueue transformationQueue = new TransformationQueue();
        transformationQueue.setBranchCode(casaTransformerQueuePopulatorResponse.getBranch_code());
        transformationQueue.setEntityCode(casaTransformerQueuePopulatorResponse.getEntity_code());
        transformationQueue.setElRunDate(casaTransformerQueuePopulatorResponse.getEl_run_date());
        transformationQueue.setReferenceNumber(casaTransformerQueuePopulatorResponse.getCustomer_account_number());
        transformationQueue.setTransformationProcessStatus(ProcessStatus.UNPROCESSED);
        transformationQueue.setQueuedTimeStamp(new Timestamp(new Date().getTime()));
        transformationQueue.setModule(ModuleCode.CASA.toString());
        log.debug("Processor returning CTQ {}", transformationQueue.toString());
        return transformationQueue;
    }
}
