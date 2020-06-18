package com.profinch.fincluez.fincluezcasatransformer.rowMapper;

import com.profinch.fincluez.fincluezcasatransformer.models.ExchangeRateModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExchangeRateModelRowMapper implements RowMapper<ExchangeRateModel> {

    @Override
    public ExchangeRateModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExchangeRateModel exchangeRatemodel = new ExchangeRateModel();
        exchangeRatemodel.setBranchCode(rs.getString("branchcode"));
        exchangeRatemodel.setCurrency1(rs.getString("currency1"));
        exchangeRatemodel.setCurrency2(rs.getString("currency2"));
        exchangeRatemodel.setMidRate(rs.getDouble("midRate"));
        exchangeRatemodel.setPowerFactor(rs.getDouble("powerFactor"));
        exchangeRatemodel.setRateType(rs.getString("rateType"));
        return exchangeRatemodel;
    }
}
