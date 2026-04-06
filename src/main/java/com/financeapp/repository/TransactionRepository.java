package com.financeapp.repository;

import com.financeapp.model.Transaction;
import com.financeapp.model.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByDeletedFalseOrderByDateDesc();

    Optional<Transaction> findByIdAndDeletedFalse(Long id);

    @Query("SELECT t FROM Transaction t WHERE t.deleted = false " +
            "AND (:type IS NULL OR t.type = :type) " +
            "AND (:category IS NULL OR LOWER(t.category) LIKE LOWER(CONCAT('%', :category, '%'))) " +
            "AND (:from IS NULL OR t.date >= :from) " +
            "AND (:to IS NULL OR t.date <= :to) " +
            "ORDER BY t.date DESC")
    List<Transaction> findWithFilters(
            @Param("type") TransactionType type,
            @Param("category") String category,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.type = :type AND t.deleted = false")
    BigDecimal sumByType(@Param("type") TransactionType type);

    @Query("SELECT t.category, COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.deleted = false GROUP BY t.category")
    List<Object[]> getCategoryTotals();

    @Query("SELECT FUNCTION('MONTH', t.date) as month, FUNCTION('YEAR', t.date) as year, " +
            "t.type, COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.deleted = false " +
            "GROUP BY FUNCTION('YEAR', t.date), FUNCTION('MONTH', t.date), t.type " +
            "ORDER BY year DESC, month DESC")
    List<Object[]> getMonthlyTrends();

    List<Transaction> findTop10ByDeletedFalseOrderByCreatedAtDesc();
}