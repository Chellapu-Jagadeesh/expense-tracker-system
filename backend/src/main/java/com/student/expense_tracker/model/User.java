package com.student.expense_tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true,nullable=false)
    private String username;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @Column(nullable=false)
    private String password;
}