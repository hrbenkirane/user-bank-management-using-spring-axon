package com.example.bankacc.core.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FundDepositedEvent {
    private String id;
    private double amount;
    private double balance;
}
