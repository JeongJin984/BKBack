package com.example.bkback.db.repository.post;

import com.example.bkback.db.entity.Post;
import com.example.bkback.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends CommonRepository<Post, UUID>, PostRepositoryCustom {
}
