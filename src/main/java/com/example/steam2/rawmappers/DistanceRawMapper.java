package com.example.steam2.rawmappers;


import com.example.steam2.domains.CameraPeopleNumber;
import com.example.steam2.domains.Distance;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DistanceRawMapper implements RowMapper<Distance> {
    @Override
    public Distance mapRow(ResultSet rs, int rowNum) throws SQLException {
        Distance distance = new Distance();
        distance.setDistance(rs.getInt("distance"));
        distance.setTrackTime(rs.getTimestamp("track_time").toLocalDateTime());
        return distance;
    }
}
