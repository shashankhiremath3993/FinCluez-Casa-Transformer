package com.profinch.fincluez.fincluezcasatransformer.rowMapper;


import com.profinch.fincluez.fincluezcasatransformer.models.DimAccountModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DimAccountModelRowMapper implements RowMapper<DimAccountModel> {
    @Override
    public DimAccountModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        DimAccountModel dimAccountModel = new DimAccountModel();

        dimAccountModel.setAccount(rs.getString("account"));
        dimAccountModel.setAccountBrnKey(rs.getString("acccountBrnKey"));
        dimAccountModel.setBranch(rs.getString("branch"));

        return dimAccountModel;
    }
}
