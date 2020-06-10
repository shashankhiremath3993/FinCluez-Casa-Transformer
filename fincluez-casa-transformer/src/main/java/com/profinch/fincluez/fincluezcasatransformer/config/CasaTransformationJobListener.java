package com.profinch.fincluez.fincluezcasatransformer.config;

import com.profinch.fincluez.fincluezcasatransformer.constants.JobName;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.TransformationJobStatus;
import com.profinch.fincluez.finclueztlibrary.repo.martRepo.TransformationJobStatusRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Component
@Transactional(rollbackFor = Exception.class)
public class CasaTransformationJobListener implements JobExecutionListener {

    private Logger log = LoggerFactory.getLogger(CasaTransformationJobListener.class);

    @Autowired
    private TransformationJobStatusRepo transformationJobStatusRepo;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.debug("Before JOB *********** CasaTransformationService");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.debug("After JOB *********** CasaTransformationService");
        ExecutionContext executionContext = jobExecution.getExecutionContext();
        Date elRunDate = (Date) executionContext.get("elRunDate");
        log.debug("After JOB -- elRunDate is {}", elRunDate);

        TransformationJobStatus transformationJobStatus = new TransformationJobStatus();
        transformationJobStatus.setJobName(JobName.CASA_TRANSFORMATION_JOB.toString());
        transformationJobStatus.setStepName(JobName.CASA_TRANSFORMATION_JOB.toString());
        transformationJobStatus.setElRunDate(elRunDate);
        transformationJobStatus.setTimestamp(new Timestamp(System.currentTimeMillis()));
        transformationJobStatusRepo.save(transformationJobStatus);
        log.debug("Transformation Job Status -- written in After JOB *** CasaTransformationService");
    }
}
