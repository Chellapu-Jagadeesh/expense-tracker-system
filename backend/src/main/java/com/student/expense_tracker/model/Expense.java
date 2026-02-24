package com.student.expense_tracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name="expenses")
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Title is required")
    private String title;
    @Positive(message="Amount must be greater than zero")
    private Double amount;
    private String category;
    @Column(name="date_created")
    private LocalDateTime dateCreated=LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name="user_id",nullable=false)
    private User user;
}
