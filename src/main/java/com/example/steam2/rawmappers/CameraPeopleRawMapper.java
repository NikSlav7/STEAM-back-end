package com.example.steam2.rawmappers;


import com.example.steam2.domains.CameraPeopleNumber;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CameraPeopleRawMapper implements RowMapper<CameraPeopleNumber> {
    @Override
    public CameraPeopleNumber mapRow(ResultSet rs, int rowNum) throws SQLException {
        CameraPeopleNumber cameraPeopleNumber = new CameraPeopleNumber();
        cameraPeopleNumber.setNumber(rs.getInt("number"));
        cameraPeopleNumber.setTrackTime(rs.getTimestamp("track_time").toLocalDateTime());
        return cameraPeopleNumber;
    }
}
