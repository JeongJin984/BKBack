package com.example.bkback.db.repository.accountClan;

import com.example.bkback.db.entity.AccountClan;
import com.example.bkback.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountClanRepository extends CommonRepository<AccountClan, UUID>, AccountClanRepositoryCustom{
}
