package com.cetpa.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetpa.entities.AccountInfo;
import com.cetpa.entities.User;
import com.cetpa.repositories.AccountRepository;
import com.cetpa.repositories.UserRepository;
import com.cetpa.services.UserService;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired private UserRepository userRepository;
	@Autowired private AccountRepository accountRepository;

	public int createAccount(User user) 
	{
		userRepository.save(user);
		AccountInfo account=new AccountInfo();
		account.setUserid(user.getUserid());
		accountRepository.save(account);
		return account.getAccountno();
	}

	public User getUser(String userid) 
	{
		User user=userRepository.findById(userid).orElse(null);
		return user;
	}
}
