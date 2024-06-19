package com.cetpa.services;

import java.util.List;

import com.cetpa.entities.AccountInfo;
import com.cetpa.entities.Transaction;

public interface AccountService 
{
	AccountInfo getAccount(String userid);
	AccountInfo getAccount(int an);
	void updateAmount(int amount, int accountno);
	List<Transaction> getTransactionList(int accountno);
}
