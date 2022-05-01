package com.example.bankacc.query.api.handlers;

import com.example.bankacc.core.events.AccountClosedEvent;
import com.example.bankacc.core.events.AccountOpenedEvent;
import com.example.bankacc.core.events.FundsDepositedEvent;
import com.example.bankacc.core.events.FundsWithdrawnEvent;

public interface AccountEventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);
}
