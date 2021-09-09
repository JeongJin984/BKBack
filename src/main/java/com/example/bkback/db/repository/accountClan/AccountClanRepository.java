package com.example.bkback.db.repository.accountClan;

import com.example.bkback.db.entity.AccountClan;
import com.example.bkback.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountClanRepository extends CommonRepository<AccountClan, Long>, AccountClanRepositoryCustom{
}
