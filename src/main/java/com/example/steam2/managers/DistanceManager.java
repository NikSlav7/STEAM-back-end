package com.example.steam2.managers;


import com.example.steam2.dao.CameraPeopleDao;
import com.example.steam2.dao.DistanceDao;
import com.example.steam2.domains.CameraPeopleNumber;
import com.example.steam2.domains.Distance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequestMapping("/api/distance")
@RestController
public class DistanceManager {


    private final DistanceDao distanceDao;

    @Autowired
    public DistanceManager(CameraPeopleDao cameraPeopleDao, DistanceDao distanceDao) {
        this.distanceDao = distanceDao;
    }


    @PostMapping("/new")
    private void saveDistance(@RequestBody Distance distance){
        distanceDao.saveDistance(distance);
    }

    @GetMapping("/get")
    private List<Distance> getDistance(@RequestParam("number_limit") int limit){
        return distanceDao.getLatestDistance(limit);
    }
}

