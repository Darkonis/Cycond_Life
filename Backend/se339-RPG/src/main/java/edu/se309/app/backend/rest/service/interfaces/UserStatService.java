package edu.se309.app.backend.rest.service.interfaces;

import edu.se309.app.backend.rest.entity.UserStat;

import java.util.List;
import java.util.Optional;

public interface UserStatService {

    List<UserStat> findAll();

    void save(UserStat newUserStat);

    Optional<UserStat> findById(int userStatId);


}
