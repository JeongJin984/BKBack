package com.example.bkback.db.repository.postPostlist;

import com.example.bkback.db.entity.PostPostList;
import com.example.bkback.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostPostlistRepository extends CommonRepository<PostPostList, UUID>, PostPostlistRepositoryCustom {
}
