package com.example.bkback.db.repository.Intimacy;

import com.example.bkback.db.entity.Intimacy;
import com.example.bkback.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntimacyRepository extends CommonRepository<Intimacy, Long>, IntimacyRepositoryCustom {
}
