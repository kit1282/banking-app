package com.cetpa.constrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cetpa.entities.AccountInfo;
import com.cetpa.entities.Transaction;
import com.cetpa.services.AccountService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("mybank/activity")
public class AccountController 
{
	@Autowired private AccountService accountService;
	
	@RequestMapping("view-balance")
	public String getBalanceAmount(HttpSession session,Model model)
	{
		int an=(Integer)session.getAttribute("accountno");
		AccountInfo account=accountService.getAccount(an);
		int amount=account.getAmount();
		model.addAttribute("amount",amount);
		return "transaction/show-balance";
	}
	@RequestMapping("deposit-form")
	public String getDepositFormView()
	{
		return "transaction/deposit/deposit-money";
	}
	@RequestMapping("deposit-money")
	public String depositMoney(int amount,Model model,HttpSession session)
	{
		int accountno=(Integer)session.getAttribute("accountno");
		accountService.updateAmount(amount,accountno);
		model.addAttribute("amount",amount);
		return "transaction/deposit/deposit-success";
	}
	@RequestMapping("transaction-summary")
	public String getTransactionList(Model model,HttpSession session)
	{
		int accountno=(Integer)session.getAttribute("accountno");
		List<Transaction> tlist=accountService.getTransactionList(accountno);
		model.addAttribute("tlist", tlist);
		return "transaction/deposit/summary";
	}
}
