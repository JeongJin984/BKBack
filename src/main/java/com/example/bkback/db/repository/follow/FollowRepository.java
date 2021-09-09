package com.example.bkback.db.repository.follow;

import com.example.bkback.db.entity.Follow;
import com.example.bkback.db.repository.CommonRepository;
import com.example.bkback.db.repository.comment.CommentRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends CommonRepository<Follow, Long>, CommentRepositoryCustom {
}
