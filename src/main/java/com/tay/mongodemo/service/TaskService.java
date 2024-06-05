package com.tay.mongodemo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tay.mongodemo.model.Task;
import com.tay.mongodemo.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepo;

	public Task addTask(Task task) {
		task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
		return taskRepo.save(task);
	}

	public List<Task> getAllTasks() {
		return taskRepo.findAll();
	}

	public Task getTaskById(String taskId) {
		return taskRepo.findById(taskId).orElseThrow();
	}

	public List<Task> getTasksByAssignee(String assignee) {
		return taskRepo.findByAssignee(assignee);
	}

	public List<Task> getTasksBySeverity(int severity) {
		return taskRepo.findBySeverity(severity);
	}

	public Task updateTask(Task taskRequest) {
		Task existingTask = taskRepo.findById(taskRequest.getTaskId()).get();
		
		existingTask.setDescription(taskRequest.getDescription());
		existingTask.setSeverity(taskRequest.getSeverity());
		existingTask.setAssignee(taskRequest.getAssignee());
		existingTask.setStoryPoint(taskRequest.getStoryPoint());
		
		return taskRepo.save(existingTask);
	}

	public String deleteTask(String taskId) {
		taskRepo.deleteById(taskId);
		return taskId + " task deleted from dashboard ";
	}
}
