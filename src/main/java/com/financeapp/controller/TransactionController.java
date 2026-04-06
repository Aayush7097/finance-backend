package com.financeapp.controller;

import com.financeapp.dto.request.TransactionRequest;
import com.financeapp.dto.response.TransactionResponse;
import com.financeapp.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Tag(name = "Transactions", description = "Create, view, update, delete and filter financial records")
@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Create a transaction", description = "ADMIN and ANALYST only — add a new income or expense record")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TransactionRequest request) {
        try {
            return ResponseEntity.status(201).body(transactionService.create(request));
        } catch (Exception e) {
            e.printStackTrace(); // 🔥 debug
            return ResponseEntity.status(500)
                    .body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Get all transactions", description = "All roles — filter by type (INCOME/EXPENSE), category, from date, to date")
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {

        try {
            List<TransactionResponse> transactions =
                    transactionService.getAll(type, category, from, to);

            return ResponseEntity.ok(transactions);

        } catch (Exception e) {
            e.printStackTrace(); // 🔥 VERY IMPORTANT
            return ResponseEntity.status(500)
                    .body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Get one transaction by ID", description = "All roles — fetch a single transaction record by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(transactionService.getById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Update a transaction", description = "ADMIN and ANALYST only — edit an existing transaction record")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody TransactionRequest request) {

        try {
            return ResponseEntity.ok(transactionService.update(id, request));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Delete a transaction", description = "ADMIN only — soft delete, record is marked deleted but kept in database")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            transactionService.softDelete(id);
            return ResponseEntity.ok(Map.of("message", "Transaction deleted successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("Error: " + e.getMessage());
        }
    }
}