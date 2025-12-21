package com.example.steam2.managers;

import com.example.steam2.dao.CameraPeopleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Service
@RequestMapping("/api/general")
@RestController
public class GeneralManager {


    private final CameraPeopleDao cameraPeopleDao;


    @Autowired
    public GeneralManager(CameraPeopleDao cameraPeopleDao) {
        this.cameraPeopleDao = cameraPeopleDao;
    }

    @GetMapping("/all")
    public Map<String, Object> getLatestData(@RequestParam("number_limit") int limit){
        Map<String,Object> map=new HashMap<>();
        map.put("camera",  cameraPeopleDao.getLatestCameraPeopleNumber(limit));
        map.put("distance", null);

        return map;
    }
}
