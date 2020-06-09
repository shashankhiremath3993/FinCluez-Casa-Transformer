package com.profinch.fincluez.fincluezcasatransformer.transformation;

import com.profinch.fincluez.fincluezcasatransformer.constants.QueryStoreQueryId;
import com.profinch.fincluez.fincluezcasatransformer.models.CasaTransformationInputModel;
import com.profinch.fincluez.finclueztlibrary.entities.infraEntities.queryStore.QueryStore;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.TransformationQueue;
import com.profinch.fincluez.finclueztlibrary.service.QueryStoreCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class SelectCasaData {

    @Autowired
    private QueryStoreCacheService queryStoreCacheService;


    @Autowired
    @Qualifier("stagingDataSource")
    private DataSource stagingDataSource;

    public CasaTransformationInputModel selectCasaData(TransformationQueue transformationQueue){

        QueryStore queryStore = queryStoreCacheService.getQueryStoreByIdQuery(QueryStoreQueryId.SEL_CASA_DATA);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(stagingDataSource);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        ((MapSqlParameterSource) sqlParameterSource).addValue("x" ,transformationQueue.getReferenceNumber());

        CasaTransformationInputModel casaTransformationInputModel = namedParameterJdbcTemplate.queryForObject(queryStore.getQueryString(),sqlParameterSource,new CTIModelRowMapper());

        return casaTransformationInputModel;

    }

}
