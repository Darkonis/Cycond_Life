package edu.se309.app.backend.rest.service.interfaces;

import edu.se309.app.backend.rest.entity.Account;

public interface AccountService extends BaseService<Account, Integer> {

    Account findByEmail(String email);

    Account findByUsername(String username);
}
