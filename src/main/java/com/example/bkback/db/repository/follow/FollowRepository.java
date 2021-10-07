package com.example.bkback.db.repository.follow;

import com.example.bkback.db.entity.Follow;
import com.example.bkback.db.repository.CommonRepository;
import com.example.bkback.db.repository.comment.CommentRepositoryCustom;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FollowRepository extends CommonRepository<Follow, UUID>, FollowRepositoryCustom {
}
