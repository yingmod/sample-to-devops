package com.vnn.devops;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 1. Lấy danh sách tất cả Task trong Database
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // 2. Thêm một Task mới vào Database
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Map<String, String> request) {
        String title = request.getOrDefault("title", "Công việc mặc định");
        Task task = new Task(title);
        return taskRepository.save(task);
    }
}