package com.bms.projectx.controller;

import com.bms.projectx.entity.UserEntity;
import com.bms.projectx.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/users
    @GetMapping
    public ResponseEntity<List<UserEntity>> index() {
        return ResponseEntity.ok(userService.findAll());
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> show(@PathVariable Long id) {
        UserEntity user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    // POST /api/users
    @PostMapping
    public ResponseEntity<UserEntity> store(@RequestBody UserEntity user) {
        UserEntity created = userService.save(user);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> update(
            @PathVariable Long id,
            @RequestBody UserEntity user) {

        UserEntity updated = userService.update(id, user);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}