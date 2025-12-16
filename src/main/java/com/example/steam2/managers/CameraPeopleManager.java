package com.example.steam2.managers;


import com.example.steam2.dao.CameraPeopleDao;
import com.example.steam2.domains.CameraPeopleNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequestMapping("/api/camera")
@RestController
public class CameraPeopleManager {


    private final CameraPeopleDao cameraPeopleDao;

    @Autowired
    public CameraPeopleManager(CameraPeopleDao cameraPeopleDao) {
        this.cameraPeopleDao = cameraPeopleDao;
    }


    @PostMapping("/new")
    private void saveCameraPeopleNumber(@RequestBody  CameraPeopleNumber cameraPeopleNumber){
        cameraPeopleDao.savePeopleNumber(cameraPeopleNumber);
    }

    @GetMapping("/get")
    private List<CameraPeopleNumber> getCameraPeopleNumber(@RequestParam("number_limit") int limit){
        return cameraPeopleDao.getLatestCameraPeopleNumber(limit);
    }
}

