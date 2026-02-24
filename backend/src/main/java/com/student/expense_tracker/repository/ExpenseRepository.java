package com.student.expense_tracker.repository;

import com.student.expense_tracker.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
    List<Expense> findByUserId(Long userId);
}