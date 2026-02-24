package com.student.expense_tracker.controller;

import com.student.expense_tracker.model.*;
import com.student.expense_tracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins="*")
public class ExpenseController {
    @Autowired private ExpenseRepository repository;
    @GetMapping
    public ResponseEntity<?> getAllExpenses(Authentication authentication){
        User user=(User) authentication.getPrincipal();
        List<Expense> expenses=repository.findByUserId(user.getId());
        return ResponseEntity.ok(expenses);
    }
    @PostMapping
    public ResponseEntity<?> addExpense(@RequestBody Expense expense, Authentication authentication){
        User user=(User) authentication.getPrincipal();
        expense.setUser(user);
        return ResponseEntity.ok(repository.save(expense));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable("id") Long id, Authentication authentication){
        User user=(User) authentication.getPrincipal();
        Optional<Expense> expense=repository.findById(id);
        if(expense.isEmpty() || !expense.get().getUser().getId().equals(user.getId())){
            return ResponseEntity.status(403).build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}