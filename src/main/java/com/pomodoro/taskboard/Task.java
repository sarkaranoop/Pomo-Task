package com.pomodoro.taskboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor

public class Task {

    private String id;

    private String title;

    private String description;

    private String status;

    private int pomodoroEstimated;

    private int pomodoroCompleted;

    public Task() {
        this.id = UUID.randomUUID().toString();
        this.status = "todo";
        this.pomodoroEstimated = 1;
        this.pomodoroCompleted = 0;
    }

}
