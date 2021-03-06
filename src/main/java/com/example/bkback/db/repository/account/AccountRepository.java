package com.example.bkback.db.repository.account;

import com.example.bkback.db.entity.Account;
import com.example.bkback.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends CommonRepository<Account, UUID>, AccountRepositoryCustom {
}
