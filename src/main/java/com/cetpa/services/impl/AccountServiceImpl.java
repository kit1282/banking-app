package com.cetpa.services.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetpa.entities.AccountInfo;
import com.cetpa.entities.Transaction;
import com.cetpa.repositories.AccountRepository;
import com.cetpa.repositories.TransactionRepository;
import com.cetpa.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService 
{
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository; 

	public AccountInfo getAccount(String userid) 
	{
		AccountInfo account=accountRepository.findByUserid(userid);
		return account;
	}
	public AccountInfo getAccount(int an) 
	{
		AccountInfo account=accountRepository.findById(an).orElse(null);
		return account;
	}
	public void updateAmount(int amount, int accountno) 
	{
		//code to update amount in Account class
		accountRepository.updateUserAmount(amount, accountno);
		//code to create and persist object of Transaction class
		saveTransactionDetails(amount, accountno);
	} 
	private void saveTransactionDetails(int amount,int accountno)
	{
		Transaction transaction=new Transaction();
		transaction.setAccountno(accountno);
		transaction.setAmount(amount);
		transaction.setDate(LocalDate.now().toString());
		transaction.setTime(LocalTime.now().toString());
		transaction.setType("credit");
		transactionRepository.save(transaction);
	}
	public List<Transaction> getTransactionList(int accountno) 
	{
		return transactionRepository.findByAccountno(accountno);
	}
}
