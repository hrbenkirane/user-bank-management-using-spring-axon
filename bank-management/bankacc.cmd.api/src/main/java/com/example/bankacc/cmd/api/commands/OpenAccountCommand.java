package com.example.bankacc.cmd.api.commands;

import com.example.bankacc.core.models.AccountType;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class OpenAccountCommand {
    @TargetAggregateIdentifier
    private  String id;
    private String accountHolderId;
    private AccountType accountType;
    private double openingBalance;
}