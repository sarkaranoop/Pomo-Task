package com.pomodoro.taskboard.service;

import com.pomodoro.taskboard.dto.TaskRequest;
import com.pomodoro.taskboard.dto.TaskResponse;
import com.pomodoro.taskboard.entity.Task;
import com.pomodoro.taskboard.entity.User;
import com.pomodoro.taskboard.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskResponse> getUserTasks(User user) {
        return taskRepository.findByUser(user)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public TaskResponse createTask(TaskRequest request, User user) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus() != null ? request.getStatus() : "todo");
        task.setPomodoroEstimated(request.getPomodoroEstimated());
        task.setPomodoroCompleted(0);
        task.setUser(user);
        return toResponse(taskRepository.save(task));
    }

    public TaskResponse updateTask(Long id, TaskRequest request, User user) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // make sure user owns this task
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPomodoroEstimated(request.getPomodoroEstimated());
        task.setPomodoroCompleted(request.getPomodoroCompleted());
        return toResponse(taskRepository.save(task));
    }

    public void deleteTask(Long id, User user) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        taskRepository.delete(task);
    }

    // convert Task entity → TaskResponse DTO
    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPomodoroEstimated(),
                task.getPomodoroCompleted(),
                task.getCreatedAt()
        );
    }
}