package com.example.bkback.db.repository.likedComment;

import com.example.bkback.db.entity.LikedComment;
import com.example.bkback.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LikedCommentRepository extends CommonRepository<LikedComment, UUID>, LikedCommentRepositoryCustom {
}
