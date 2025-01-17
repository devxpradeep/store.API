package com.rumble.store.repository;

import com.rumble.store.entity.Tool;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends MongoRepository<Tool, String>, PagingAndSortingRepository<Tool,String> {
}
