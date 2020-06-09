package com.profinch.fincluez.fincluezcasatransformer.config;

import com.profinch.fincluez.fincluezcasatransformer.constants.JobName;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.jobStatus.TransformationJobStatus;
import com.profinch.fincluez.finclueztlibrary.repo.martRepo.jobStatus.TransformationJobStatusRepo;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Configuration
public class CasaTransformationJobLauncher implements CommandLineRunner {

    Logger log = LoggerFactory.getLogger(CasaTransformationJobLauncher.class);

    @Autowired
    @Qualifier("casaTransformationJob")
    private Job casaTransformationJob;

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private TransformationJobStatusRepo transformationJobStatusRepo;

    private Date getToday() {
        Date today = new Date();
        DateUtils.truncate(today, Calendar.DATE);
        return today;
    }

    private Date getElRunDateToBeRun() {
        log.debug("AppLauncher --> Inside....getElRunDateToBeRun.....");
        Date elRunDateToBeRun = new Date();
        List<TransformationJobStatus> transformationJobStatusList =
                transformationJobStatusRepo.findTop1ByJobNameAndStepNameOrderByElRunDateDesc
                        (JobName.CASA_TRANSFORMATION_JOB.toString()
                                , JobName.CASA_TRANSFORMATION_JOB.toString());
        log.debug("AppLauncher --> transformationJobStatusList....{}", transformationJobStatusList.toString());
        if (transformationJobStatusList != null
                && !transformationJobStatusList.isEmpty()
                && transformationJobStatusList.size() > 0) {
            log.debug("Found the last EL Run Date as {}", transformationJobStatusList.get(0).getElRunDate());
            elRunDateToBeRun = DateUtils.addDays(transformationJobStatusList.get(0).getElRunDate(), 1);
        }
        DateUtils.truncate(elRunDateToBeRun, Calendar.DATE);
        log.debug("AppLauncher --> Returning....ElRunDateToBeRun...as {}", elRunDateToBeRun);
        return elRunDateToBeRun;
    }

    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);

        return jobLauncher;
    }


    public JobParameters getJobParametersFromCmdLine(String[] args, Date elRunDate) {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        // param 0
        jobParametersBuilder.addString("entityCode", args[0]);
        // param 1
        jobParametersBuilder.addString("branchCode", args[1]);

        jobParametersBuilder.addDate("elRunDate", elRunDate);

        jobParametersBuilder.addLong("currentTime", new Date().getTime());
        return jobParametersBuilder.toJobParameters();
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("***** Casa Transformation Job Launcher - Launched");
        JobParameters jobParameters;

        Date elRunDateToBeRun = getElRunDateToBeRun();
        Date today = getToday();

        while (elRunDateToBeRun.before(today) || elRunDateToBeRun.equals(today)) {
            log.debug("Launching CASA Transformation for Date => {}", elRunDateToBeRun);
            jobParameters = getJobParametersFromCmdLine(args, elRunDateToBeRun);
            jobLauncher.run(casaTransformationJob, jobParameters);
            elRunDateToBeRun = getElRunDateToBeRun();
        }

    }

}

