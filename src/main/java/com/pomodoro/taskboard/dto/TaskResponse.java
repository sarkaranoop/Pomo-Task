package com.pomodoro.taskboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private String status;
    private int pomodoroEstimated;
    private int pomodoroCompleted;
    private LocalDateTime createdAt;
}