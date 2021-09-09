package com.example.bkback.db.repository.postlist;

import com.example.bkback.db.entity.Postlist;
import com.example.bkback.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostlistRepository extends CommonRepository<Postlist, Long>, PostlistRepositoryCustom {

}
