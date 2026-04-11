package com.pomodoro.taskboard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String status;

    @Column(name = "pomodoro_estimated")
    private int pomodoroEstimated;

    @Column(name = "pomodoro_completed")
    private int pomodoroCompleted;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    public void prePersist() {
        this.status = this.status != null ? this.status : "todo";
        this.pomodoroEstimated = this.pomodoroEstimated > 0 ? this.pomodoroEstimated : 1;
        this.createdAt = LocalDateTime.now();
    }
}