package com.profinch.fincluez.fincluezcasatransformer.rowMapper;

import com.profinch.fincluez.fincluezcasatransformer.models.CasaModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SelCasaCustInfoRowMapper implements RowMapper<CasaModel> {

    @Override
    public CasaModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            CasaModel casaModel = new CasaModel();
            casaModel.setCustomerNo(rs.getString("customerNo"));
            casaModel.setCustomerName1(rs.getString("customerName1"));
            casaModel.setShortName(rs.getString("shortName"));
            casaModel.setCountry(rs.getString("country"));
            casaModel.setCustomerCategory(rs.getString("customerCategory"));
            casaModel.setCustomerType(rs.getString("customerType"));
            casaModel.setLiabilityNo(rs.getInt("liabilityNo"));


            return casaModel;
        
    }
}
