package com.example.steam2.repos;

import com.example.steam2.domains.CameraPeopleNumber;
import com.example.steam2.domains.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DistanceRepo extends JpaRepository<Distance, LocalDateTime> {
    Optional<Distance> findDistanceByDistance(float distance);
    Optional<Distance> findDistanceByTrackTime(LocalDateTime trackTime);


}
