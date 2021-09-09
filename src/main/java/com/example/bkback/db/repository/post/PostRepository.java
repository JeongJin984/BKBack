package com.example.bkback.db.repository.post;

import com.example.bkback.db.entity.Post;
import com.example.bkback.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CommonRepository<Post, Long>, PostRepositoryCustom {
}
