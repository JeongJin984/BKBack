package com.example.bkback.db.repository.likedPost;

import com.example.bkback.db.entity.LikedPost;
import com.example.bkback.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LikedPostRepository extends CommonRepository<LikedPost, UUID>, LikedPostRepositoryCustom {
}
