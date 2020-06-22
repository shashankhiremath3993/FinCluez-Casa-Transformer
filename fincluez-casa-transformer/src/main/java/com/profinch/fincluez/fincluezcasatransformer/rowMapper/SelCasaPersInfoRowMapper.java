package com.profinch.fincluez.fincluezcasatransformer.rowMapper;

import com.profinch.fincluez.fincluezcasatransformer.models.CasaModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SelCasaPersInfoRowMapper  implements RowMapper<CasaModel> {

    @Override
    public CasaModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        CasaModel casaCustPersonalInfoModel = new CasaModel();
        casaCustPersonalInfoModel.setDateOfBirth(rs.getDate("dateOfBirth"));
        casaCustPersonalInfoModel.setCustomerNo(rs.getString("customerNo"));
        casaCustPersonalInfoModel.setSex(rs.getString("sex"));
        return casaCustPersonalInfoModel;
    }
}