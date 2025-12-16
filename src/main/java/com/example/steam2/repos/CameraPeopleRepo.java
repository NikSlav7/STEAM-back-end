package com.example.steam2.repos;

import com.example.steam2.domains.CameraPeopleNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CameraPeopleRepo extends JpaRepository<CameraPeopleNumber, LocalDateTime> {
    Optional<CameraPeopleNumber> findByNumber(int number);
    Optional<CameraPeopleNumber> findCameraPeopleNumberByTrackTime(LocalDateTime trackTime);


}
