package com.profinch.fincluez.fincluezcasatransformer.transformation;

import com.profinch.fincluez.finclueztlibrary.constants.ProcessStatus;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.TransformationQueue;
import com.profinch.fincluez.finclueztlibrary.transformer.TransformerInterface;
import com.profinch.fincluez.finclueztlibrary.transformer.TransformerOutputModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class CasaTransformationService implements TransformerInterface {

    private Logger log = LoggerFactory.getLogger(CasaTransformationService.class);


    @Override
    public TransformerOutputModel performTransformation(TransformationQueue transformationQueue) {

        transformationQueue.setTransformationProcessStatus(ProcessStatus.PROCESSED);
        transformationQueue.setProcessedTimeStamp(new Timestamp(new Date().getTime()));

        return null;

    }
}
