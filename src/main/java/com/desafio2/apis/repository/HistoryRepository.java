package com.desafio2.apis.repository;

import com.desafio2.apis.domain.History;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryRepository extends MongoRepository<History, String> {
}
