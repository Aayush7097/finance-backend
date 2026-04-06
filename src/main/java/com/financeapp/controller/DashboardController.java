package com.financeapp.controller;

import com.financeapp.dto.response.DashboardSummary;
import com.financeapp.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Dashboard", description = "Summary analytics — totals, trends, recent activity")
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(
            summary = "Get dashboard summary",
            description = "Returns total income, total expenses, net balance, " +
                    "category wise totals, recent 10 transactions and monthly trends. " +
                    "Accessible by ADMIN, ANALYST and VIEWER roles."
    )
    @GetMapping("/summary")
    public ResponseEntity<?> getSummary() {
        try {
            DashboardSummary summary = dashboardService.getSummary();
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            e.printStackTrace(); // 🔥 VERY IMPORTANT (shows real error in console)

            return ResponseEntity.status(500)
                    .body("Error: " + e.getMessage());
        }
    }
}