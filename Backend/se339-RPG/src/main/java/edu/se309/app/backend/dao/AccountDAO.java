package edu.se309.app.backend.dao;

import java.util.List;

import edu.se309.app.backend.entity.Account;

public interface AccountDAO {

	public List<Account> findAll();
}
