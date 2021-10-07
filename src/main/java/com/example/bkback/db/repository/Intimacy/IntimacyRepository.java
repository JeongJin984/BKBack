package com.example.bkback.db.repository.Intimacy;

import com.example.bkback.db.entity.Intimacy;
import com.example.bkback.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IntimacyRepository extends CommonRepository<Intimacy, UUID>, IntimacyRepositoryCustom {
}
