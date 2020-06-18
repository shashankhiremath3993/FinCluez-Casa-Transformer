package com.profinch.fincluez.fincluezcasatransformer.transformation;

import com.profinch.fincluez.fincluezcasatransformer.constants.QueryStoreQueryId;
import com.profinch.fincluez.fincluezcasatransformer.models.CasaTransformationInputModel;
import com.profinch.fincluez.fincluezcasatransformer.rowMapper.CTIModelRowMapper;
import com.profinch.fincluez.finclueztlibrary.entities.infraEntities.QueryStore;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.TransformationQueue;
import com.profinch.fincluez.finclueztlibrary.service.QueryStoreCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class SelectCasaData {
    private Logger log = LoggerFactory.getLogger(SelectCasaData.class);


    @Autowired
    private QueryStoreCacheService queryStoreCacheService;


    @Autowired
    @Qualifier("stagingDataSource")
    private DataSource stagingDataSource;

    public CasaTransformationInputModel selectCasaData(TransformationQueue transformationQueue){

        /* SEL_CASA_DATA
        a.branch_code = :branchCode AND
        a.entity_code = :entityCode AND
        a.el_run_date = :elRunDate AND
        a.cust_ac_no = :customerAccountNumber
        */

        log.debug("Inside....selectCasaData...with TQ {}",transformationQueue.toString());

        QueryStore queryStore = queryStoreCacheService.getQueryStoreByIdQuery(QueryStoreQueryId.SEL_CASA_DATA);
        log.debug("Query Store for SEL_CASA_DATA - retrieved {}",queryStore.getQueryString());

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(stagingDataSource);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        ((MapSqlParameterSource) sqlParameterSource).addValue("branchCode" ,transformationQueue.getBranchCode());
        ((MapSqlParameterSource) sqlParameterSource).addValue("entityCode" ,transformationQueue.getEntityCode());
        ((MapSqlParameterSource) sqlParameterSource).addValue("elRunDate" ,transformationQueue.getElRunDate());
        ((MapSqlParameterSource) sqlParameterSource).addValue("customerAccountNumber" ,transformationQueue.getReferenceNumber());

        CasaTransformationInputModel casaTransformationInputModel = namedParameterJdbcTemplate.queryForObject(queryStore.getQueryString(),sqlParameterSource,new CTIModelRowMapper());
        log.debug("CTIModel retrieved...is {}",casaTransformationInputModel.toString());

        return casaTransformationInputModel;


    }

}
