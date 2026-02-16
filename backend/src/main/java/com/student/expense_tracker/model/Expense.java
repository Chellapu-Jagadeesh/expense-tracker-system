package com.student.expense_tracker.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name="expenses")
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double amount;
    private String category;
    @Column(name="date_created")
    private LocalDateTime dateCreated=LocalDateTime.now();
}
