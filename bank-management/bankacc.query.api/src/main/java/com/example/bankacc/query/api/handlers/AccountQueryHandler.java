package com.example.bankacc.query.api.handlers;

import com.example.bankacc.query.api.dto.AccountLookupResponse;
import com.example.bankacc.query.api.queries.FindAccountByHolderIdQuery;
import com.example.bankacc.query.api.queries.FindAccountByIdQuery;
import com.example.bankacc.query.api.queries.FindAccountsWithBalanceQuery;
import com.example.bankacc.query.api.queries.FindAllAccountsQuery;

public interface AccountQueryHandler {
    AccountLookupResponse findAccountById(FindAccountByIdQuery query);
    AccountLookupResponse findAccountByHolderId(FindAccountByHolderIdQuery query);
    AccountLookupResponse findAllAccounts(FindAllAccountsQuery query);
    AccountLookupResponse findAccountsWithBalance(FindAccountsWithBalanceQuery query);
}
