package com.profinch.fincluez.fincluezcasatransformer.batch.step_2;

import com.profinch.fincluez.fincluezcasatransformer.models.CasaTransformationOutputModel;
import com.profinch.fincluez.fincluezcasatransformer.transformation.CasaTransformationService;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.TransformationQueue;
import com.profinch.fincluez.finclueztlibrary.transformer.TransformerOutputModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@StepScope
public class CasaTransformationProcessor implements ItemProcessor<TransformationQueue, CasaTransformationOutputModel> {

    private Logger log = LoggerFactory.getLogger(CasaTransformationProcessor.class);

    @Autowired
    private CasaTransformationService casaTransformationService;

    @Override
    public CasaTransformationOutputModel process(TransformationQueue
                                                         transformationQueue) throws Exception {
        log.debug("Inside Processor with {}", transformationQueue.toString());

        TransformerOutputModel transformerOutputModel= casaTransformationService.performTransformation(transformationQueue);

        CasaTransformationOutputModel casaTransformationOutputModel = (CasaTransformationOutputModel) transformerOutputModel;
        log.debug("Processor returning CTQ {}", casaTransformationOutputModel.toString());
        return casaTransformationOutputModel;
    }

}
