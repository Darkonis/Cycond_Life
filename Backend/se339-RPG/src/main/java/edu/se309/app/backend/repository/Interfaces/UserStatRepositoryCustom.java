package edu.se309.app.backend.repository.Interfaces;

import edu.se309.app.backend.entity.Account;
import edu.se309.app.backend.entity.UserStat;

public interface UserStatRepositoryCustom {

	UserStat findByAccount(Integer account);
}
