package com.pomodoro.taskboard;

import com.pomodoro.taskboard.entity.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/tasks")
public class TaskControllerOld {
    private final Map<String, Task> taskStore = new ConcurrentHashMap<>();

    @GetMapping
    public List<Task> getAlltasks(){
        return new ArrayList<>(taskStore.values());
    }

    @PostMapping
    public Task createTask(@RequestBody Task task){
       Task newtask = new Task();

        newtask.setTitle(task.getTitle());
        newtask.setDescription(task.getDescription());
        newtask.setPomodoroEstimated(task.getPomodoroEstimated());
        taskStore.put(newtask.getId(), newtask);

        return newtask;

    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task task) {
        Task existing = taskStore.get(id);
        if (existing == null) return ResponseEntity.notFound().build();

        existing.setTitle(task.getTitle());
        existing.setDescription(task.getDescription());
        existing.setStatus(task.getStatus());
        existing.setPomodoroEstimated(task.getPomodoroEstimated());
        existing.setPomodoroCompleted(task.getPomodoroCompleted());

        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        if (taskStore.remove(id) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
