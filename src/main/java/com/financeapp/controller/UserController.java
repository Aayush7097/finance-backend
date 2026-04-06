package com.financeapp.controller;

import com.financeapp.model.User;
import com.financeapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Tag(name = "Users", description = "User management — ADMIN only")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Get all users",
            description = "ADMIN only — returns list of all users in the system"
    )
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllActiveUsers());
    }

    @Operation(
            summary = "Get user by ID",
            description = "ADMIN only — fetch a single user by their ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(
            summary = "Toggle user active or inactive status",
            description = "ADMIN only — if user is active it becomes inactive, if inactive it becomes active"
    )
    @PatchMapping("/{id}/toggle-status")
    public ResponseEntity<Map<String, Object>> toggleStatus(@PathVariable Long id) {
        User updated = userService.toggleUserStatus(id);
        return ResponseEntity.ok(Map.of(
                "message", "User status updated",
                "userId", updated.getId(),
                "active", updated.isActive()
        ));
    }

    @Operation(
            summary = "Delete a user permanently",
            description = "ADMIN only — completely removes the user from the system"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }
}