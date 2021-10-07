package com.example.bkback.db.repository.postHashtag;

import com.example.bkback.db.entity.PostHashtag;
import com.example.bkback.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostHashtagRepository extends CommonRepository<PostHashtag, UUID>, PostHashtagRepositoryCustom {
}
