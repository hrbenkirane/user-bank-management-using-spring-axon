package com.example.bankacc.query.api.repositories;

import com.example.bankacc.core.models.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<BankAccount, String> {
}
