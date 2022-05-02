package com.example.bankacc.query.api.handlers;

import com.example.bankacc.core.models.BankAccount;
import com.example.bankacc.query.api.dto.AccountLookupResponse;
import com.example.bankacc.query.api.dto.EqualityType;
import com.example.bankacc.query.api.queries.FindAccountByHolderIdQuery;
import com.example.bankacc.query.api.queries.FindAccountByIdQuery;
import com.example.bankacc.query.api.queries.FindAccountsWithBalanceQuery;
import com.example.bankacc.query.api.queries.FindAllAccountsQuery;
import com.example.bankacc.query.api.repositories.AccountRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountQueryHandlerImpl implements AccountQueryHandler {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountQueryHandlerImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountById(FindAccountByIdQuery query) {
        Optional<BankAccount> bankAccount = accountRepository.findById(query.getId());
        AccountLookupResponse response = bankAccount.isPresent()
                ? new AccountLookupResponse("Bank Account successfully returned!", bankAccount.get())
                : new AccountLookupResponse("No Bank account found for id - " + query.getId());
        return response;
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountByHolderId(FindAccountByHolderIdQuery query) {
        Optional<BankAccount> bankAccount = accountRepository.findByAccountHolderId(query.getAccountHolderId());
        AccountLookupResponse response = bankAccount.isPresent()
                ? new AccountLookupResponse("Bank Account successfully returned!", bankAccount.get())
                : new AccountLookupResponse("No Bank account found for account holder id - " + query.getAccountHolderId());
        return response;
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAllAccounts(FindAllAccountsQuery query) {
        Iterable<BankAccount> bankAccountIterator = accountRepository.findAll();
        if(!bankAccountIterator.iterator().hasNext()) {
            return new AccountLookupResponse("No Bank accounts were found!");
        }
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccountIterator.forEach(i -> bankAccounts.add(i));
        int count = bankAccounts.size();
        return new AccountLookupResponse("Successfully Return" + count + " Bank Account(s)!", bankAccounts);
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountsWithBalance(FindAccountsWithBalanceQuery query) {
       List<BankAccount> bankAccounts = query.getEqualityType().equals(EqualityType.GREATER_THAN)
               ? accountRepository.findByBalanceGreaterThan(query.getBalance())
               : accountRepository.findByBalanceLessThan(query.getBalance());
       AccountLookupResponse response = bankAccounts != null && bankAccounts.size() > 0
               ? new AccountLookupResponse("Successfully returned " + bankAccounts.size() + " Bank Account(s)!", bankAccounts)
               : new AccountLookupResponse("No Bank Accounts were found!");
       return response;
    }
}
