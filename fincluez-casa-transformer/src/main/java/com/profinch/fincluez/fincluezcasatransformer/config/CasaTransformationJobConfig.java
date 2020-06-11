package com.profinch.fincluez.fincluezcasatransformer.config;

import com.profinch.fincluez.fincluezcasatransformer.batch.step_1.CasaTransformationQueuePopulatorProcessor;
import com.profinch.fincluez.fincluezcasatransformer.batch.step_1.CasaTransformationQueuePopulatorWriter;
import com.profinch.fincluez.fincluezcasatransformer.batch.step_2.CasaTransformationProcessor;
import com.profinch.fincluez.fincluezcasatransformer.batch.step_2.CasaTransformationWriter;
import com.profinch.fincluez.fincluezcasatransformer.models.CasaTransformationOutputModel;
import com.profinch.fincluez.fincluezcasatransformer.models.TransformationQueueModel;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.TransformationQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@EnableBatchProcessing
@Configuration
public class CasaTransformationJobConfig {

    private Logger log = LoggerFactory.getLogger(CasaTransformationJobConfig.class);

    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;


    //Job Listener
    @Autowired
    JobExecutionListener casaTransformationJobListener;

    //Step_0
    @Autowired
    @Qualifier("validationTasklet")
    private Tasklet validationTasklet;
    @Autowired
    @Qualifier("validationTaskletListener")
    private StepExecutionListener validationTaskletListener;

    //Step-1
    @Autowired
    @Qualifier("casaTransformationQueuePopulatorReader")
    private ItemReader casaTransformationQueuePopulatorReader;
    @Autowired
    @Qualifier("casaTransformationQueuePopulatorProcessor")
    private CasaTransformationQueuePopulatorProcessor casaTransformationQueuePopulatorProcessor;
    @Autowired
    @Qualifier("casaTransformationQueuePopulatorWriter")
    private CasaTransformationQueuePopulatorWriter casaTransformationQueuePopulatorWriter;
    @Autowired
    @Qualifier("casaTransformationQueuePopulatorListener")
    private StepExecutionListener casaTransformationQueuePopulatorListener;

    //Step-2
    @Autowired
    @Qualifier("casaTransformationReader")
    private ItemReader casaTransformationReader;
    @Autowired
    @Qualifier("casaTransformationProcessor")
    private CasaTransformationProcessor casaTransformationProcessor;
    @Autowired
    @Qualifier("casaTransformationWriter")
    private CasaTransformationWriter casaTransformationWriter;
    @Autowired
    @Qualifier("casaTransformationListener")
    private StepExecutionListener casaTransformationListener;


    public CasaTransformationJobConfig() {
        log.info("Creating beans for FinCluez CasaTransformationService BatchProcessorJob!!");
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("FinCluez-CasaTransformer-");
    }

    //Step-0
    @Bean
    Step validationTaskletStep() {
        return stepBuilderFactory.get("validationTaskletStep")
                .tasklet(validationTasklet)
                .listener(validationTaskletListener)
                .throttleLimit(1)
                .build();
    }

    //Step-1
    @Bean
    Step casaTransformationQueuePopulatorStep() {
        log.debug("Creating casaTransformationQueuePopulatorStep");
        SimpleStepBuilder builder =
                (SimpleStepBuilder) stepBuilderFactory.get("casaTransformationQueuePopulatorStep")
                        .<TransformationQueueModel, TransformationQueue>chunk(5)
                        .reader(casaTransformationQueuePopulatorReader)
                        .processor(casaTransformationQueuePopulatorProcessor)
                        .writer(casaTransformationQueuePopulatorWriter)
                        .taskExecutor(taskExecutor())
                        .throttleLimit(1);
        builder.listener((StepExecutionListener) casaTransformationQueuePopulatorListener);
        return builder.build();
    }


    //Step-2
    @Bean
    Step casaTransformationStep() {
        log.debug("Creating casaTransformationStep");
        SimpleStepBuilder builder = (SimpleStepBuilder) stepBuilderFactory.get("casaTransformationStep")
                .<TransformationQueue, CasaTransformationOutputModel>chunk(5)
                .reader(casaTransformationReader)
                .processor(casaTransformationProcessor)
                .writer(casaTransformationWriter)
                .taskExecutor(taskExecutor())
                .throttleLimit(1);
        builder.listener((StepExecutionListener) casaTransformationListener);
        return builder.build();
    }


    @Bean
    public Job casaTransformationJob(
            @Qualifier("validationTaskletStep") Step validationTaskletStep,
            @Qualifier("casaTransformationQueuePopulatorStep") Step casaTransformationQueuePopulatorStep,
            @Qualifier("casaTransformationStep") Step casaTransformationStep
    ) {
        log.debug("Creating casaTransformationJob");
        return jobBuilderFactory.get("casaTransformationJob")
                .incrementer(new RunIdIncrementer())
                //Step-0 to Step-1
                .start(validationTaskletStep)
                    .on("run-Step-1")
                    .to(casaTransformationQueuePopulatorStep)
                .from(validationTaskletStep)
                    .on("skip-Step-1")
                    .to(casaTransformationStep)
                .from(casaTransformationQueuePopulatorStep)
                    .on("COMPLETED")
                    .to(casaTransformationStep)
                .end()
                .listener(casaTransformationJobListener)
                .build();
    }

}
