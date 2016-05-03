package com.persist.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.persist.model.Account;
import com.persist.model.AccountRepository;

@RestController
class AccountController {

	private final AccountRepository accountRepository;

    @RequestMapping(value="/accounts", method = RequestMethod.GET)
    Collection<Account> getAllAccounts() {
    	return this.accountRepository.findAll();
    }

	@RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
	Account readAccount(@PathVariable String userId, @PathVariable Long accountId) {
		this.validateUser(userId);
		return this.accountRepository.findOne(accountId);
	}
	@RequestMapping(method = RequestMethod.GET)
	Optional<Account> readAccounts(@PathVariable String userId) {
		this.validateUser(userId);
		return this.accountRepository.findByUsername(userId);
	}

	@Autowired
	AccountController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	private void validateUser(String userId) {
		this.accountRepository.findByUsername(userId).orElseThrow(
				() -> new UserNotFoundException(userId));
	}
}
@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7902689465940032367L;

	public UserNotFoundException(String userId) {
		super("could not find user '" + userId + "'.");
	}
}