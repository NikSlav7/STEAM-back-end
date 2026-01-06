package com.example.steam2.dao;

import com.example.steam2.domains.CameraPeopleNumber;
import com.example.steam2.domains.Distance;
import com.example.steam2.rawmappers.CameraPeopleRawMapper;
import com.example.steam2.rawmappers.DistanceRawMapper;
import com.example.steam2.repos.CameraPeopleRepo;
import com.example.steam2.repos.DistanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DistanceDao {


    private final DistanceRepo distanceRepo;
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public DistanceDao(CameraPeopleRepo cameraPeopleRepo, DistanceRepo distanceRepo, JdbcTemplate jdbcTemplate) {
        this.distanceRepo = distanceRepo;
        this.jdbcTemplate = jdbcTemplate;
    }


    public Distance saveDistance(Distance distance){
        distanceRepo.save(distance);
        return distance;
    }

    public  List<Distance> getLatestDistance(int limit){
       List<Distance> list = jdbcTemplate.query("SELECT * FROM distance ORDER BY track_time desc LIMIT " + limit, new DistanceRawMapper());
       return list.isEmpty()? null : list;
    }
}

