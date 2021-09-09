package com.example.bkback.db.repository.postCategory;

import com.example.bkback.db.entity.PostCategory;
import com.example.bkback.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCategoryRepository extends CommonRepository<PostCategory, Long>, PostCategoryRepositoryCustom {
}
