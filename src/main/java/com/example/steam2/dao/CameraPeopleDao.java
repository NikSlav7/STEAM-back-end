package com.example.steam2.dao;

import com.example.steam2.domains.CameraPeopleNumber;
import com.example.steam2.rawmappers.CameraPeopleRawMapper;
import com.example.steam2.repos.CameraPeopleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CameraPeopleDao {


    private final CameraPeopleRepo cameraPeopleRepo;
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public CameraPeopleDao(CameraPeopleRepo cameraPeopleRepo, JdbcTemplate jdbcTemplate) {
        this.cameraPeopleRepo = cameraPeopleRepo;
        this.jdbcTemplate = jdbcTemplate;
    }


    public CameraPeopleNumber savePeopleNumber(CameraPeopleNumber cameraPeopleNumber){
        cameraPeopleRepo.save(cameraPeopleNumber);
        return cameraPeopleNumber;
    }

    public  List<CameraPeopleNumber> getLatestCameraPeopleNumber(int limit){
       List<CameraPeopleNumber> list = jdbcTemplate.query("SELECT * FROM camera_people_number ORDER BY track_time desc LIMIT " + limit, new CameraPeopleRawMapper());
       return list.isEmpty()? null : list;
    }
}

