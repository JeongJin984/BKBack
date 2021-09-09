package com.example.bkback.db.repository.clan;

import com.example.bkback.db.entity.Clan;
import com.example.bkback.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClanRepository extends CommonRepository<Clan, Long>, ClanRepositoryCustom {
}
