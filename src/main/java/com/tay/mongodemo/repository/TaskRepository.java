package com.tay.mongodemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tay.mongodemo.model.Task;
import java.util.List;


@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
	
	List<Task> findByAssignee(String assignee);
	List<Task> findBySeverity(int severity);
}
