package edu.se309.app.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.se309.app.backend.entity.UserStat;
import edu.se309.app.backend.service.interfaces.StatService;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

	private StatService statService;

	@Autowired
	public StatsController(StatService statService) {
		this.statService = statService;
	}

	@GetMapping("/count")
	public long count() {
		return statService.count();
	}

	@GetMapping("/")
	public List<UserStat> findAll() {
		return statService.findAll();
	}

	@PutMapping("/incrementStat/{accountId}/{stat}")
	public UserStat incrementStat(@PathVariable("accountId") Integer accountId, @PathVariable("stat") String stat) {
		return statService.incrementByOne(accountId, stat);
	}

	@PutMapping("/updateStats/{accountId}")
	public UserStat modifyUserStats(@PathVariable Integer accountId, @RequestBody UserStat userStat) {
		UserStat userStatWithAccount = statService.findByAccountId(accountId);
		userStatWithAccount.copyStats(userStat);
		statService.save(userStatWithAccount);
		return userStatWithAccount;
	}

	@PutMapping("/updateStat/{accountId}/{stat}/{value}")
	public UserStat updateStat(@PathVariable int accountId, String stat, int value) {
		return statService.updateUserStat(accountId, stat, value);
	}

}
