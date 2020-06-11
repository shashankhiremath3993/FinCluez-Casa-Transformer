package com.profinch.fincluez.fincluezcasatransformer.transformation;

import com.profinch.fincluez.fincluezcasatransformer.entities.martEntities.Casa;
import com.profinch.fincluez.fincluezcasatransformer.models.CasaTransformationInputModel;
import com.profinch.fincluez.fincluezcasatransformer.models.CasaTransformationOutputModel;
import com.profinch.fincluez.finclueztlibrary.constants.ProcessStatus;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.TransformationQueue;
import com.profinch.fincluez.finclueztlibrary.transformer.TransformerInterface;
import com.profinch.fincluez.finclueztlibrary.transformer.TransformerOutputModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class CasaTransformationService implements TransformerInterface {

    private Logger log = LoggerFactory.getLogger(CasaTransformationService.class);

    @Autowired
    private SelectCasaData selectCasaData;

    @Override
    public TransformerOutputModel performTransformation(TransformationQueue transformationQueue) {

        transformationQueue.setTransformationProcessStatus(ProcessStatus.PROCESSED);
        transformationQueue.setProcessedTimeStamp(new Timestamp(new Date().getTime()));

        Casa casaMart = new Casa();

        CasaTransformationInputModel ctiModelFrom_SEL_CASA_DATA = selectCasaData.selectCasaData(transformationQueue);
        casaMart.setAccountBrnKey(ctiModelFrom_SEL_CASA_DATA.getBranchCode()+"--"+ctiModelFrom_SEL_CASA_DATA.getCustAcNo());
        casaMart.setEntityCode(ctiModelFrom_SEL_CASA_DATA.getEntityCode());
        casaMart.setAmtCurrentBalanceAcy(ctiModelFrom_SEL_CASA_DATA.getAcyCurrBalance());

        CasaTransformationOutputModel casaTransformationOutputModel = new CasaTransformationOutputModel();
        casaTransformationOutputModel.setTransformationQueue(transformationQueue);
        casaTransformationOutputModel.setCasa(casaMart);

        log.debug("returning...ctoModel from CasaTransformationService....with {}",casaTransformationOutputModel.toString());
        return casaTransformationOutputModel;

    }
}
