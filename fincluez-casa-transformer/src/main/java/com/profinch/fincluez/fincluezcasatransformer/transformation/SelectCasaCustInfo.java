package com.profinch.fincluez.fincluezcasatransformer.transformation;

import com.profinch.fincluez.fincluezcasatransformer.constants.QueryStoreQueryId;
import com.profinch.fincluez.fincluezcasatransformer.models.CasaModel;
import com.profinch.fincluez.fincluezcasatransformer.rowMapper.SelCasaCustInfoRowMapper;
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
public class SelectCasaCustInfo  {
    private Logger log = LoggerFactory.getLogger(SelectCasaCustInfo.class);

    @Autowired
    private QueryStoreCacheService queryStoreCacheService;


    @Autowired
    @Qualifier("stagingDataSource")
    private DataSource stagingDataSource;

    public CasaModel getSelCasaCustInfo(TransformationQueue transformationQueue){
        log.debug("Inside....getSelCasaCustInfo...with transformationQueue : {} ",transformationQueue);

        QueryStore queryStore = queryStoreCacheService.getQueryStoreByIdQuery(QueryStoreQueryId.SEL_CASA_CUST_INFO);
        log.debug("Query Store for SEL_CASA_DIM_ACCOUNT - retrieved {}",queryStore.getQueryString());

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(stagingDataSource);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        ((MapSqlParameterSource) sqlParameterSource).addValue("customerAccountNumber" ,transformationQueue.getReferenceNumber());
        ((MapSqlParameterSource) sqlParameterSource).addValue("entityCode" ,transformationQueue.getEntityCode());



        CasaModel casaModel =  namedParameterJdbcTemplate.queryForObject(queryStore.getQueryString(),sqlParameterSource,new SelCasaCustInfoRowMapper());
        log.debug("casaModel retrieved...is {}",casaModel.toString());

        return casaModel;


    }


}
