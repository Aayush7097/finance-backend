package com.financeapp.service;

import com.financeapp.dto.request.TransactionRequest;
import com.financeapp.dto.response.TransactionResponse;
import com.financeapp.exception.ResourceNotFoundException;
import com.financeapp.model.Transaction;
import com.financeapp.model.User;
import com.financeapp.model.enums.TransactionType;
import com.financeapp.repository.TransactionRepository;
import com.financeapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional // 🔥 IMPORTANT (fixes LazyInitializationException)
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionResponse create(TransactionRequest request) {
        User currentUser = getCurrentUser();

        Transaction transaction = Transaction.builder()
                .amount(request.getAmount())
                .type(request.getType())
                .category(request.getCategory().trim())
                .date(request.getDate())
                .notes(request.getNotes())
                .createdBy(currentUser)
                .build();

        return toResponse(transactionRepository.save(transaction));
    }

    public List<TransactionResponse> getAll(String type, String category, LocalDate from, LocalDate to) {

        TransactionType transactionType = null;

        if (type != null && !type.isBlank()) {
            try {
                transactionType = TransactionType.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid type. Use INCOME or EXPENSE");
            }
        }

        return transactionRepository
                .findWithFilters(transactionType, category, from, to)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public TransactionResponse getById(Long id) {
        Transaction t = transactionRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));

        return toResponse(t);
    }

    public TransactionResponse update(Long id, TransactionRequest request) {

        Transaction existing = transactionRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));

        existing.setAmount(request.getAmount());
        existing.setType(request.getType());
        existing.setCategory(request.getCategory().trim());
        existing.setDate(request.getDate());
        existing.setNotes(request.getNotes());

        return toResponse(transactionRepository.save(existing));
    }

    public void softDelete(Long id) {

        Transaction transaction = transactionRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));

        transaction.setDeleted(true);
        transactionRepository.save(transaction);
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Authenticated user not found"));
    }

    public TransactionResponse toResponse(Transaction t) {

        // 🔥 SAFE ACCESS (prevents crash)
        String createdByName = "Unknown";

        if (t.getCreatedBy() != null) {
            createdByName = t.getCreatedBy().getFullName();
        }

        return TransactionResponse.builder()
                .id(t.getId())
                .amount(t.getAmount())
                .type(t.getType())
                .category(t.getCategory())
                .date(t.getDate())
                .notes(t.getNotes())
                .createdAt(t.getCreatedAt())
                .createdBy(createdByName)
                .build();
    }
}