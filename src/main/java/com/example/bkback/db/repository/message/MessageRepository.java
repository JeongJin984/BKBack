package com.example.bkback.db.repository.message;

import com.example.bkback.db.entity.Message;
import com.example.bkback.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends CommonRepository<Message, UUID>, MessageRepositoryCustom {
}
