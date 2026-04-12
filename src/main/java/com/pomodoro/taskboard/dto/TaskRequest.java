package com.pomodoro.taskboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {
    private String title;
    private String description;
    private String status;
    private int pomodoroEstimated;
    private int pomodoroCompleted;
}