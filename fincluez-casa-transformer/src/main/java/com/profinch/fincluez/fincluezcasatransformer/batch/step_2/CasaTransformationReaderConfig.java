package com.profinch.fincluez.fincluezcasatransformer.batch.step_2;


import com.profinch.fincluez.fincluezcasatransformer.constants.ModuleCode;
import com.profinch.fincluez.finclueztlibrary.constants.ProcessStatus;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.TransformationQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CasaTransformationReaderConfig {

    private Logger log = LoggerFactory.getLogger(CasaTransformationReaderConfig.class);


    @Autowired
    @Qualifier("martDataSource")
    private DataSource martDataSource;



    @Bean
    @StepScope
    public ItemReader<? extends TransformationQueue> casaTransformationQueue
    (@Value("#{jobParameters['entityCode']}") String entityCode,
     @Value("#{jobParameters['branchCode']}") String branchCode,
     @Value("#{jobParameters['elRunDate']}") Date elRunDate) {
        JdbcPagingItemReader<TransformationQueue> reader = new JdbcPagingItemReader<TransformationQueue>();
        final SqlPagingQueryProviderFactoryBean sqlPagingQueryProviderFactoryBean = new SqlPagingQueryProviderFactoryBean();
        sqlPagingQueryProviderFactoryBean.setDataSource(martDataSource);
        sqlPagingQueryProviderFactoryBean.setSelectClause("select * ");
        sqlPagingQueryProviderFactoryBean.setFromClause("from transformation_queue");
        sqlPagingQueryProviderFactoryBean.setWhereClause("where transformationProcessStatus =" + ProcessStatus.UNPROCESSED+
                " and module ="+ ModuleCode.CASA +
                " and entity_code = :entityCode" +
                " and branch_code = :branchCode" +
                " and el_run_date = :elRunDate");
        sqlPagingQueryProviderFactoryBean.setSortKey(" referenceNumber");

        Map<String, Object> parameterValues = new HashMap<>();
        parameterValues.put("entityCode", entityCode);
        parameterValues.put("branchCode", branchCode);
        parameterValues.put("elRunDate", elRunDate);


        try {
            reader.setQueryProvider(sqlPagingQueryProviderFactoryBean.getObject());
            reader.setParameterValues(parameterValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        reader.setDataSource(martDataSource);
        reader.setPageSize(5);
        reader.setRowMapper(new BeanPropertyRowMapper<TransformationQueue>(TransformationQueue.class));
        return reader;

    }
}

