package com.example.bkback.db.repository.postCategory;

import com.example.bkback.db.entity.PostCategory;
import com.example.bkback.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostCategoryRepository extends CommonRepository<PostCategory, UUID>, PostCategoryRepositoryCustom {
}
