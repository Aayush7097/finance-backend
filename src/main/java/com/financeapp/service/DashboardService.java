package com.financeapp.service;

import com.financeapp.dto.response.DashboardSummary;
import com.financeapp.dto.response.TransactionResponse;
import com.financeapp.model.enums.TransactionType;
import com.financeapp.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    @Transactional // 🔥 VERY IMPORTANT (fixes LazyInitializationException)
    public DashboardSummary getSummary() {

        // ✅ Handle NULL values safely
        BigDecimal totalIncome = transactionRepository.sumByType(TransactionType.INCOME);
        BigDecimal totalExpense = transactionRepository.sumByType(TransactionType.EXPENSE);

        if (totalIncome == null) totalIncome = BigDecimal.ZERO;
        if (totalExpense == null) totalExpense = BigDecimal.ZERO;

        BigDecimal netBalance = totalIncome.subtract(totalExpense);

        // ✅ Category totals
        Map<String, BigDecimal> categoryTotals = new LinkedHashMap<>();
        List<Object[]> categoryData = transactionRepository.getCategoryTotals();

        if (categoryData != null) {
            for (Object[] row : categoryData) {
                if (row[0] != null && row[1] != null) {
                    categoryTotals.put((String) row[0], (BigDecimal) row[1]);
                }
            }
        }

        // ✅ Recent transactions (FIXED Lazy loading issue)
        List<TransactionResponse> recent = transactionRepository
                .findTop10ByDeletedFalseOrderByCreatedAtDesc()
                .stream()
                .map(transactionService::toResponse)
                .collect(Collectors.toList());

        // ✅ Monthly trends
        List<Map<String, Object>> monthlyTrends = new ArrayList<>();
        List<Object[]> monthlyData = transactionRepository.getMonthlyTrends();

        if (monthlyData != null) {
            for (Object[] row : monthlyData) {
                Map<String, Object> entry = new LinkedHashMap<>();
                entry.put("month", row[0]);
                entry.put("year", row[1]);
                entry.put("type", row[2]);
                entry.put("total", row[3]);
                monthlyTrends.add(entry);
            }
        }

        // ✅ Final response
        return DashboardSummary.builder()
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .netBalance(netBalance)
                .categoryTotals(categoryTotals)
                .recentTransactions(recent)
                .monthlyTrends(monthlyTrends)
                .build();
    }
}